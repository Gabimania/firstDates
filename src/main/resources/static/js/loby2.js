
document.addEventListener("DOMContentLoaded", function() {
    const cargarChatPublicoBtn = document.getElementById("global-chats-tab");
    const contenidoPublicoDiv = document.getElementById("contenidoPublico");

    cargarChatPublicoBtn.addEventListener("click", function() {
        fetch("public")
            .then(response => {
                if (!response.ok) {
                    throw new Error("No se pudo cargar el chat público.");
                }
                return response.text();
            })
            .then(data => {
                contenidoPublicoDiv.innerHTML = data;
            })
            .catch(error => {
                console.error("Error al cargar el chat público:", error);
            });
    });
});


document.addEventListener("DOMContentLoaded", function() {
    const cargarDateBtn = document.getElementById("my-chats-tab");
    const contenidoDateDiv = document.getElementById("contenidoPublico");

    cargarDateBtn.addEventListener("click", function() {
        fetch("chat")
            .then(response => {
                if (!response.ok) {
                    throw new Error("No se pudo cargar el chat público.");
                }
                return response.text();
            })
            .then(data => {
                contenidoDateDiv.innerHTML = data;
            })
            .catch(error => {
                console.error("Error al cargar el chat público:", error);
            });
    });
});


document.addEventListener("DOMContentLoaded", function() {
    const cargarRequestBtn = document.getElementById("my-requests-tab");
    const contenidoRequestDiv = document.getElementById("contenidoPublico");

    cargarRequestBtn.addEventListener("click", function() {
        fetch("request")
            .then(response => {
                if (!response.ok) {
                    throw new Error("No se pudo cargar el chat público.");
                }
                return response.text();
            })
            .then(data => {
                contenidoRequestDiv.innerHTML = data;
            })
            .catch(error => {
                console.error("Error al cargar el chat público:", error);
            });
    });
});



document.addEventListener("DOMContentLoaded", function() {
    const cargarChatPublicoBtn = document.getElementById("available-dates-tab");
    const contenidoAvaibleDateDiv = document.getElementById("contenidoPublico");

    cargarChatPublicoBtn.addEventListener("click", function() {
        fetch("avaible")
            .then(response => {
                if (!response.ok) {
                    throw new Error("No se pudo cargar el chat público.");
                }
                return response.text();
            })
            .then(data => {
                contenidoAvaibleDateDiv.innerHTML = data;
            })
            .catch(error => {
                console.error("Error al cargar el chat público:", error);
            });
    });
});

/*
document.addEventListener("DOMContentLoaded", function() {
    const cargarChatPrvBtn = document.getElementById("my-chat");
    const contenidoAvaibleDateDiv = document.getElementById("contenidoPublico");

    cargarChatPrvBtn.addEventListener("click", function() {
        fetch("user")
            .then(response => {
                if (!response.ok) {
                    throw new Error("No se pudo cargar el chat público.");
                }
                return response.text();
            })
            .then(data => {
                contenidoAvaibleDateDiv.innerHTML = data;
            })
            .catch(error => {
                console.error("Error al cargar el chat público:", error);
            });
    });
});*/












(function() {
    var node = document.querySelector(".htmlmenu-header");
    var stop = node.offsetTop;
    var window_last_position = 0;
    var scroll = window.requestAnimationFrame ||
        window.webkitRequestAnimationFrame ||
        window.mozRequestAnimationFrame ||
        window.msRequestAnimationFrame ||
        window.oRequestAnimationFrame ||
        // IE Fallback, you can even fallback to onscroll
        function(callback) {
            window.setTimeout(callback, 1000 / 60)
        };

    var loop = function() {
        // avoid calculations if not needed
        if (window_last_position === window.pageYOffset) {
            scroll(loop);
            return false;
        } else window_last_position = window.pageYOffset;

        //... calculations
        if (window_last_position > stop) {
            // stick the div
            if (node.className.indexOf(" htmlmenu-header-scrolled") === -1) {
                node.className = node.className + ' htmlmenu-header-scrolled';
            }
        } else {
            // release the div
            node.className = node.className.indexOf(' htmlmenu-header-scrolled') >= 0 ? node.className.replace(' htmlmenu-header-scrolled', '') : node.className;
        }

        scroll(loop);
    };

    // call the loop for the first time
    loop();
})();





