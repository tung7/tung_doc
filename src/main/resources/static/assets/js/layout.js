$(function() {
    generateNav2("#nav", window.navRoot);
    generateNav2("#m-nav", window.navRoot);
    activeNav("#nav");
    activeNav("#m-nav");
    console.log(window.navRoot);

    $(".accordion li a").on("click",function() {
        var $this = $(this);
        expandNav($(this));
    });

    var $mobMenu                = $('#m-nav'),
        $toc                 = $("#toc"),
        $tocSticky           = $('.nav .ui.sticky'),
        $fullHeightContainer = $('.pusher > .full.height');

    // create sidebar sticky
    $tocSticky.sticky({
        silent: true,
        context: $fullHeightContainer
    });



    // launch buttons
    $mobMenu.sidebar('attach events', '.launch.button, .view-ui, .launch.item');

    function expandNav($this) {
        var $targetContent = $($this.parent().find(" > ul"));
        $this.toggleClass("expaned");
        $this.find("i.icon.angle").toggleClass("right");
        $this.find("i.icon.angle").toggleClass("down");
        $targetContent.slideToggle("fast");
    }

    function activeNav(id) {
        var path = location.pathname;
        console.log(path);

        $(id).find("a").each(function (idx, item) {
            console.log($(this).attr("href"));
            if ($(this).attr("href") === path) {
                $(this).addClass("active");
                var parentULs = $(this).parents('ul.sub');
                var parentLi = $(this).parent();
                var parentLis = parentLi.parents('li');
                parentLis.each(function(){
                    // $(this).trigger('click');
                    var $this = $(this).find('>a');
                    expandNav($this);
                });

            } else {
                $(this).removeClass("active");
            }
        });

    }



    function generateNav2(id, root) {
        if (root == null) {
            return;
        }
        var rootItem = $("<li>")
            .append(
                $("<a>").addClass("title").attr("href", "/").append(
                    $("<span>").addClass("ui logo icon image")
                        .append(
                            $("<img>").attr("src", "/assets/images/z.jpg")
                        )
                ).append(
                    $("<b>").text("Tung Docs")
                )
            );

        var rootCategory = $("<li>").append($("<a>").addClass("title").attr("href", '/category/' + root.id)
            .append(
                $("<b>").text(root.name)
            ));
        var wrp = $(id).append(rootItem).append(rootCategory).append(generateSubNavs2(root.subNavs, "1."));
        // $(id).append(wrp);
    }

    function generateSubNavs2(nodeList, taxis) {
        var result = [];
        var reg = /(\d+)\.$/;
        taxis=taxis+"";

        for (var k in nodeList) {
            var node = nodeList[k];
            // if (node.subNavs.length == 0) {
            if (node.type == 'LINK') {
                result.push(generateNavAItem2(node, taxis));
            } else {
                result.push(generateNavGroupItem2(node, taxis, taxis));
            }

            var matchs = taxis.match(reg);
            var newTaxNum = 1 + matchs[1]*1;
            taxis = taxis.replace(reg , newTaxNum+".");
        }
        return result;
    }

    function generateNavAItem2(node, taxis) {
        var li = $("<li>").append($("<a>").attr("href", '/category/' + navRoot.id + '/article/' + node.id).addClass("title").attr("data-id", node.id).html(
            taxis + " " + node.name
        ));
        return li;

    }

    function generateNavGroupItem2(node, taxis) {
        var li = $("<li>").append($("<a>").addClass("title").attr("data-id", node.id).html(
            "<b>" + taxis + " " + node.name + '</b><i class="angle right icon"></i>'
        )).append(
            $("<ul>").addClass("sub").append(
                generateSubNavs2(node.subNavs, taxis+"1.")
            )
        );

        return li;
    }

});

