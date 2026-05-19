// Registro

const formulario = document.getElementById("formulario");

const nombre = document.getElementById("Nombre");
const apellido = document.getElementById("Apellido");
const email = document.getElementById("email");
const dni = document.getElementById("DNI");
const telefono = document.getElementById("telefono");
const contraseña = document.getElementById("contrasena");
const contraseña2 = document.getElementById("contrasena2");

const errorFormulario = document.getElementById("errorFormulario");
const mensajeForm = document.getElementById("mensajeForm");

if (formulario) {
formulario.addEventListener("submit", (event)  => {
    event.preventDefault();

    const valorNombre = nombre.value.trim();
    const valorApellido = apellido.value.trim();
    const valorEmail = email.value.trim();
    const valorDNI = dni.value.trim();
    const valorTelefono = telefono.value.trim();
    const valorContra = contraseña.value.trim();
    const valorContras = contraseña2.value.trim();



    errorFormulario.textContent = "";
    mensajeForm.classList.remove("contact-form__success--error");


    if (valorNombre === "") {
        errorFormulario.textContent = "El campo nombre es obligatorio.";
        mensajeForm.textContent = "No se ha podido enviar el formulario.";
        mensajeForm.classList.add("contact-form__success--error");
        return;
    }

    if (valorEmail === "") {
        errorFormulario.textContent = "El correo electrónico es obligatorio.";
        mensajeForm.textContent = "No se ha podido enviar el formulario.";
        mensajeForm.classList.add("contact-form__success--error");
        return;
    }

    if (!valorEmail.includes("@") || !valorEmail.includes(".")) {
        errorFormulario.textContent = "El correo electrónico no tiene un formato válido.";
        mensajeForm.textContent = "No se ha podido enviar el formulario.";
        mensajeForm.classList.add("contact-form__success--error");
        return;
    }

    if (valorDNI === "") {
        errorFormulario.textContent = "El campo DNI es obligatorio.";
        mensajeForm.textContent = "No se ha podido enviar el formulario.";
        mensajeForm.classList.add("contact-form__success--error");
        return;
    }

    const regexDNI = /^\d{8}[A-Z]$/;
        if (!regexDNI.test(valorDNI)) {
            errorFormulario.textContent = "El DNI debe tener 8 números y 1 letra (Ej: 12345678Z).";
            mensajeForm.textContent = "No se ha podido enviar el formulario.";
            mensajeForm.classList.add("contact-form__success--error");
            return;
        }

    if (valorTelefono === "") {
        errorFormulario.textContent = "El campo teléfono es obligatorio.";
        mensajeForm.textContent = "No se ha podido enviar el formulario.";
        mensajeForm.classList.add("contact-form__success--error");
        return;
    }

    if (valorContra === "") {
        errorFormulario.textContent = "El campo contraseña es obligatorio.";
        mensajeForm.textContent = "No se ha podido enviar el formulario.";
        mensajeForm.classList.add("contact-form__success--error");
        return;
    }
    
    if (valorContras === "") {
        errorFormulario.textContent = "El campo contraseña es obligatorio.";
        mensajeForm.textContent = "No se ha podido enviar el formulario.";
        mensajeForm.classList.add("contact-form__success--error");
        return;
    }

    if (valorContras !== valorContra) {
        errorFormulario.textContent = "Las contraseñas no coinciden.";
        mensajeForm.textContent = "No se ha podido enviar el formulario.";
        mensajeForm.classList.add("contact-form__success--error");
        return;
    }

    sessionStorage.setItem("nombreUsuario", valorNombre);
    


     mensajeForm.textContent =
     "Formulario enviado correctamente. Gracias.";
     mensajeForm.style.color = "green";
     mensajeForm.style.justifyContent = "center";
     formulario.reset();
          window.location.href = "dashboard.html";

});
}

  const nombreGuardado = sessionStorage.getItem("nombreUsuario");
const elementoNombreMenu = document.getElementById("user__nombre");    
const iconoPersona = document.getElementById("usuario");
const letraInicial = document.getElementById("avatar-inicial");

// Si hay alguien guardado en la sesión, pintamos sus datos en el menú lateral
if (nombreGuardado && elementoNombreMenu) {
    // Ponemos el nombre real (ej: "Carlos", "Manuel", "Diego")
    elementoNombreMenu.textContent = nombreGuardado;

    // Sacamos de forma dinámica la inicial de ese usuario específico
    const primeraLetra = nombreGuardado.charAt(0);

    // Si los elementos del avatar existen en la página actual, hacemos el cambio
    if (letraInicial && iconoPersona) {
        letraInicial.textContent = primeraLetra; // Mete la inicial del usuario real
        iconoPersona.style.display = "none";     // Oculta el monigote genérico
        letraInicial.style.display = "flex";     // Muestra el círculo de la letra
    }
}
    


/*DASHBOARD*/

const cardInput = document.getElementById("cardInput");

const addCardBtn = document.getElementById("addCardBtn");

const cardContainer = document.getElementById("cardContainer");

const emptyMessage = document.getElementById("emptyMessage");

const errorMessage = document.getElementById("errorMessage");

const cardCounter = document.getElementById("cardCounter");


function addNewCard() {
    const inputValue = cardInput.value.trim();

    if (inputValue === "") {
        errorMessage.textContent = "Debes escribir un texto antes de añadir un nuevo elemento.";
        return;
    }

    errorMessage.textContent = "";

    createCard(inputValue);

    cardInput.value = "";

    cardInput.focus();
}

function createCard(cardText) {
    const card = document.createElement("article");
    card.classList.add("dash__card");

    const cardHeader = document.createElement("div");
    cardHeader.classList.add("dash__card-header");

    const badge = document.createElement("span");
    badge.classList.add("dash__card-badge");
    badge.textContent = "Nuevo pedido";

    cardHeader.appendChild(badge);

    const cardTextElement = document.createElement("p");
    cardTextElement.classList.add("dash__card-text");
    cardTextElement.textContent = cardText.charAt(0).toUpperCase() + cardText.slice(1);

    const cardActions = document.createElement("div");
    cardActions.classList.add("dash__card-actions");

    const toggleButton = document.createElement("button");
    toggleButton.classList.add("dash__card-button", "dash__card-button--toggle");
    toggleButton.type = "button";
    toggleButton.textContent = "Activar estado";

    const deleteButton = document.createElement("button");
    deleteButton.classList.add("dash__card-button", "dash__card-button--delete");
    deleteButton.type = "button";
    deleteButton.textContent = "Eliminar";

    cardActions.appendChild(toggleButton);
    cardActions.appendChild(deleteButton);

    card.appendChild(cardHeader);
    card.appendChild(cardTextElement);
    card.appendChild(cardActions);

    cardContainer.appendChild(card);

    toggleButton.addEventListener("click", function () {
        card.classList.toggle("dash__card--active");

        if (card.classList.contains("dash__card--active")) {
            toggleButton.textContent = "Desactivar estado";
        } else {
            toggleButton.textContent = "Activar estado";
        }
    });

    deleteButton.addEventListener("click", function () {
        card.remove();

        updateInterfaceState();
    });

    updateInterfaceState();
}

function updateInterfaceState() {
    const totalCards = cardContainer.children.length;

    if (totalCards === 0) {
        emptyMessage.style.display = "block";
    } else {
        emptyMessage.style.display = "none";
    }

    if (totalCards === 1) {
        cardCounter.textContent = "1 pedido";
    } else {
        cardCounter.textContent = totalCards + " pedidos";
    }
}

function addNewCard() {
    const inputValue = cardInput.value.trim();

    if (inputValue === "") {
        errorMessage.textContent = "Debes escribir un texto antes de añadir un nuevo pedido.";
        return;
    }

    errorMessage.textContent = "";

    createCard(inputValue);

    cardInput.value = "";

    cardInput.focus();
}


addCardBtn.addEventListener("click", addNewCard);

cardInput.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        addNewCard();
    }
});


updateInterfaceState();