$(function() {
    generateNav("#nav", window.navRoot);
    generateNav("#m-nav", window.navRoot);
    activeNav("#nav");
    activeNav("#m-nav");
    console.log(window.navRoot);

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



    function activeNav(id) {
        var path = location.pathname;
        console.log(path);

        $(id).find("a").each(function (idx, item) {
            console.log($(this).attr("href"));
            if ($(this).attr("href") === path) {
                $(this).addClass("active");
            } else {
                $(this).removeClass("active");
            }
        });

    }

    function generateNav(id, root) {
        if (root == null) {
            return;
        }
        var rootItem = $("<div>").addClass("item")
            .append(
                $("<a>").addClass("ui logo icon image").attr("href", "/")
                    .append(
                        $("<img>").attr("src", "/assets/images/z.jpg")
                    )
            )
            .append(
                $("<a>").attr("href", "/")
                    .append(
                        $("<b>").text("Tung Docs")
                    )
            );

        var rootCategory = $("<a>").addClass("item").attr("href", root.href)
            .append(
                $("<b>").text(root.name)
            );

        $(id).append(rootItem).append(rootCategory).append(generateSubNavs(root.subNavs, "1."));
    }

    function generateSubNavs(nodeList, taxis) {
        var result = [];
        var reg = /(\d+)\.$/;
        taxis=taxis+"";

        for (var k in nodeList) {
            var node = nodeList[k];
            // if (node.subNavs.length == 0) {
            if (node.href !== null) {
                result.push(generateNavAItem(node, taxis));
            } else {
                result.push(generateNavGroupItem(node, taxis, taxis));
            }

            var matchs = taxis.match(reg);
            var newTaxNum = 1 + matchs[1]*1;
            taxis = taxis.replace(reg , newTaxNum+".");
        }
        return result;
    }

    function generateNavAItem(node, taxis) {
        return $("<a>").attr("href", node.href).addClass("item").attr("data-id", node.id).html(
            taxis + " " + node.name
            // "<i class='file text outline icon'></i>" + taxis + " " + node.name
        );
    }

    function generateNavGroupItem(node, taxis) {
        return $("<div>").addClass("item").attr("data-id", node.id).append(
            $("<div>").addClass("header").append($("<b>").html(
                    taxis + " " + node.name
                // "<i class='folder icon'></i>" + taxis + " " + node.name
                )
            )
        ).append(
            $('<div class="menu">').addClass("menu").append(
                generateSubNavs(node.subNavs, taxis+"1.")
            )
        );
    }
});

