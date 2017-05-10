$(function() {
    generateNav("#nav", window.navRoot);
    generateNav("#m-nav", window.navRoot);
    activeNav();

    $('#side-hidden-btn').on('click', function(){
        $("#nav").toggleClass("side-hidden");
        $("#main").toggleClass("side-hidden");
        $("#header").toggleClass("side-hidden");
        setTimeout(function () {
            editor.resize();
        }, 300);
    });

    function activeNav() {
        $("#nav").find("a.item").each(function (idx, item) {
            if ($(this).attr("href") === location.pathname) {
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
                $("<a>").attr("href", root.href)
                    .append(
                        $("<b>").text(root.name)
                    )
            ).append($("<i>").addClass("sidebar icon").attr("id", "side-hidden-btn"));

        $(id).append(rootItem).append(generateSubNavs(root.subNavs));
    }

    function generateSubNavs(nodeList) {
        var result = [];
        for (var k in nodeList) {
            var node = nodeList[k];
            if (node.subNavs.length == 0) {
                result.push(generateNavAItem(node));
            } else {
                result.push(generateNavGroupItem(node));
            }
        }
        return result;
    }

    function generateNavAItem(node) {
        return $("<a>").attr("href", node.href).addClass("item").attr("data-id", node.id).text(
            node.name
        );
    }

    function generateNavGroupItem(node) {
        return $("<div>").addClass("item").attr("data-id", node.id).append(
            $("<div>").addClass("header").append($("<b>").text(node.name))
        ).append(
            $('<div class="menu">').addClass("menu").append(
                generateSubNavs(node.subNavs)
            )
        );
    }
});

