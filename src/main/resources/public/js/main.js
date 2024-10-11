(function () {
    "use strict";

    var treeviewMenu = document.querySelector(".app-menu");
    var sidebarToggle = document.querySelector('[data-toggle="sidebar"]');
    var appContainer = document.querySelector(".app");

    // Toggle Sidebar
    sidebarToggle.addEventListener("click", function (event) {
        event.preventDefault();
        appContainer.classList.toggle("sidenav-toggled");
    });

    // Cerrar el Sidebar al tocar fuera de Ã©l
    document.addEventListener("click", function (event) {
        var targetElement = event.target;
        var isClickInsideSidebar =
            treeviewMenu.contains(targetElement) ||
            sidebarToggle.contains(targetElement);
        if (!isClickInsideSidebar) {
            appContainer.classList.remove("sidenav-toggled");
        }
    });

    // Activate sidebar treeview toggle
    var treeviewToggle = document.querySelectorAll("[data-toggle='treeview']");
    treeviewToggle.forEach(function (toggle) {
        toggle.addEventListener("click", function (event) {
            event.preventDefault();
            var parent = toggle.parentNode;
            if (!parent.classList.contains("is-expanded")) {
                var expandedItems = treeviewMenu.querySelectorAll(
                    "[data-toggle='treeview']"
                );
                expandedItems.forEach(function (item) {
                    item.parentNode.classList.remove("is-expanded");
                });
            }
            parent.classList.toggle("is-expanded");
        });
    });

    var treeviewToggle = document.querySelectorAll(
        "[data-toggle='treeview-sub']"
    );
    treeviewToggle.forEach(function (toggle) {
        toggle.addEventListener("click", function (event) {
            event.preventDefault();
            var parent = toggle.nextElementSibling;
            if (parent.classList.contains("treeview-menu-open")) {
                var expandedItems = treeviewMenu.querySelectorAll("[data-toggle='treeview-sub']");
                expandedItems.forEach(function (item) {
                    item.nextElementSibling.classList.add("treeview-menu-open");
                });
            }
            if (parent.className.includes("treeview-menu-open"))
                parent.classList.remove("treeview-menu-open");
            else parent.classList.add("treeview-menu-open");
        });
    });

    // Set initial active toggle
    var initialTogglesub = document.querySelector(
        "[data-toggle='treeview-sub.'].is-expanded"
    );
    if (initialTogglesub) {
        initialTogglesub.parentNode.classList.toggle("treeview-menu-open");
    }
    // Set initial active toggle
    var initialToggle = document.querySelector(
        "[data-toggle='treeview.'].is-expanded"
    );
    if (initialToggle) {
        initialToggle.parentNode.classList.toggle("is-expanded");
    }
    // Activate bootstrap tooltips
    var tooltips = document.querySelectorAll("[data-toggle='tooltip']");
    tooltips.forEach(function (tooltip) {
        new bootstrap.Tooltip(tooltip);
    });
})();
