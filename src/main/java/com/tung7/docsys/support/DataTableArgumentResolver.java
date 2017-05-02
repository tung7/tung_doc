package com.tung7.docsys.support;

import com.tung7.docsys.bean.vo.datatable.DataTable;
import com.tung7.docsys.bean.vo.datatable.DataTableColumns;
import com.tung7.docsys.bean.vo.datatable.DataTableOrder;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO to confirm [With Spring 3.1.0 the ArgumentResolver has now changed to HandlerMethodArgumentResolver - prior to that it used to be WebArgumentResolver]
 * Spring MVC arguments injection for DataTable.<br/>
 * For version 1.10 above of Jquey.DataTable Plugin.
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/3.
 * @update
 */
public class DataTableArgumentResolver implements WebArgumentResolver {
    @Override
    public Object resolveArgument(MethodParameter mp, NativeWebRequest webRequest) throws Exception {
        if(mp.getParameterType() != null && mp.getParameterType().equals(DataTable.class)){
            HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
            DataTable dataTable = new DataTable();
            Map<String, String[]> paramMap = request.getParameterMap();
            for(Map.Entry<String, String[]> entry:paramMap.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue()[0];
                switch (key) {
                    case "draw" : dataTable.setDraw(Integer.valueOf(value)); break;
                    case "start" : dataTable.setStart(Integer.valueOf(value)); break;
                    case "length" : dataTable.setLength(Integer.valueOf(value)); break;
                    default: handleOthers(key, value, dataTable);break;
                }
            }
            return dataTable;
        }
        return UNRESOLVED;
    }

    public void handleOthers(String key , String value, DataTable dataTable){
        /* order */
        Pattern p = Pattern.compile("order\\[(\\d+)\\]\\[(\\w*)\\]");
        Matcher m = p.matcher(key);
        if(m.matches()){
            int indx = Integer.valueOf(m.group(1));
            String prop = m.group(2);
            ArrayList<DataTableOrder> orderList = dataTable.getOrder();
            DataTableOrder order = null;
            if(orderList.size() > indx) {
                order = orderList.get(indx);
            } else {
                order = new DataTableOrder();
            }
            switch (prop){
                case "column": order.setColumn(Integer.valueOf(value));break;
                case "dir" : order.setDir(value);break;
            }
            if(orderList.size() <= indx) orderList.add(order);
        }

        /* search */
        p = Pattern.compile("search\\[(\\w*)\\]");
        m = p.matcher(key);
        if(m.matches()){
            String prop = m.group(1);
//            dataTable.getSearch().put(prop, value);
        }

        /* column */
        p = Pattern.compile("columns\\[(\\d)+\\]\\[(\\w*)\\](\\[(\\w*)\\])?");
        m = p.matcher(key);
        if(m.matches()){
            int indx = Integer.valueOf(m.group(1));
            String str2 = m.group(2);
            ArrayList<DataTableColumns> columns = dataTable.getColumns();
            int size = columns.size();
            DataTableColumns col = null;
            if (size > indx) {
                col = columns.get(indx);
            } else {
                col = new DataTableColumns();
            }
            switch (str2) {
                case "data": col.setData(value); break;
                case "name": col.setName(value); break;
                case "searchable": col.setSearchable(Boolean.valueOf(value)); break;
                case "orderable": col.setOrderable(Boolean.valueOf(value)); break;
                case "search":
                    String prop = m.group(2);
//                    col.getSearch().put(prop,value);
                    break;
            }
            if (size <= indx) {
                columns.add(col);
            }
        }
    }
}

