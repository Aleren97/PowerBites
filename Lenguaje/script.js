// Registro

const formulario = document.getElementById("formulario");

const nombre = document.getElementById("nombre");
const email = document.getElementById("email");
const dni = document.getElementById("DNI");
const telefono = document.getElementById("telefono");
const contraseña = document.getElementById("contraseña");
const contraseña2 = document.getElementById("contraseña2");

const errorFormulario = document.getElementById("errorFormulario");
const mensajeForm = document.getElementById("mensajeForm");

formulario.addEventListener("submit", function(event){
    event.preventDefault();

    const valorNombre = nombre.value.trim();
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

    if (valorDNI === "" || valorDNI != /^\d{8}[A-Z]$/) {
        errorFormulario.textContent = "El campo DNI es obligatorio.";
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

    if (valorContras != valorContra) {
        errorFormulario.textContent = "Las contraseñas no coinciden.";
        mensajeForm.textContent = "No se ha podido enviar el formulario.";
        mensajeForm.classList.add("contact-form__success--error");
        return;
    }

     mensajeForm.textContent =
     "Formulario enviado correctamente. Gracias.";

     formulario.reset();
});