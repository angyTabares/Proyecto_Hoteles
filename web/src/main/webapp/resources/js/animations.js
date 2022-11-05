const btnLateralPanel = document.getElementById("btnLateralPanel")
const lateralPanel = document.getElementById("lateralPanel");
const iconBtnLateralPanel = document.getElementById("iconBtnLateralPanel")


btnLateralPanel.addEventListener("click", function () {
    console.log("Click");
    if (lateralPanel.classList.contains("slideIn")) {
        lateralPanel.classList.remove("slideIn")
        btnLateralPanel.classList.remove("btnMoveOut")
        btnLateralPanel.classList.add("btnMoveIn")
        iconBtnLateralPanel.classList.remove("pi-caret-left")
        iconBtnLateralPanel.classList.add("pi-caret-right")


    } else {
        btnLateralPanel.classList.remove("btnMoveIn")
        btnLateralPanel.classList.add("btnMoveOut")
        lateralPanel.classList.add("slideIn")
        iconBtnLateralPanel.classList.remove("pi-caret-right")
        iconBtnLateralPanel.classList.add("pi-caret-left")

    }

})
