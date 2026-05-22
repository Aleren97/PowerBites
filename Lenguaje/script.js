
//Clases


class clientes {
    constructor(id, nombre, email, telefono, direccion){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}

class usuarios {
    constructor(id, nombre, email, rol, password_hash){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.password_hash = password_hash;
    }
}

class ventas {
    constructor(id, cliente_id, usuario_id, fecha, estado, total){
        this.id = id;
        this.cliente_id = cliente_id;
        this.usuario_id = usuario_id;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }
}

class productos {
    constructor(id, nombre, descripcion, precio, categoria){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }
}

class detalles_venta {
    constructor(id, venta_id, producto_id, cantidad, precioUnitario){
        this.id = id;
        this.venta_id = venta_id;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
}

const obtenerUsuariosDeSesion = () => {
    const datos = sessionStorage.getItem('crm_usuarios');
    return datos ? JSON.parse(datos) : [
        new Usuario(1, "Diego Carnicero", "diego.carnicero@estudiante.xtart.com", "Director Comercial"),
        new Usuario(2, "Beatriz Administradora", "beatriz@crm.com", "Admin")
    ];
};

const guardarClientesEnSesion = (listaClientes) => {
    sessionStorage.setItem('crm_clientes', JSON.stringify(listaClientes));
};

const obtenerClientesDeSesion = () => {
    const datos = sessionStorage.getItem('crm_clientes');
    return datos ? JSON.parse(datos) : [
        new Cliente(1, "Diego Carnicero", "diego.carnicero@estudiante.xtart.com", "555-1234", "Calle Falsa 123"),
        new Cliente(2, "Stark Ind.", "tony@stark.com", "555-9999", "Torre Stark")
    ];
};

const obtenerProductosDeSesion = () => {
    const datos = sessionStorage.getItem('crm_productos');
    // CORREGIDO: Ahora usa 'new productos' en minúscula y plural, igual que tu clase
    return datos ? JSON.parse(datos) : [
        new productos(1, "Barritas de chocolate", "Barritas energéticas de chocolate", 50.00, "Dulce"),
        new productos(2, "Barritas de fresa", "Barritas energéticas de fresa", 45.50, "Frutas"),
        new productos(3, "Barritas sin gluten", "Barritas energéticas sin gluten", 80.00, "Especiales")
    ];
};


const obtenerVentasDeSesion = () => {
    const datos = sessionStorage.getItem('crm_ventas');
    return datos ? JSON.parse(datos) : [
        new Venta(1, 1, 1, "2026-05-19", "Comercial", 1245.50),
        new Venta(2, 2, 1, "2026-05-18", "Pendiente", 180.00)
    ];
};


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

if (nombreGuardado && elementoNombreMenu) {
    elementoNombreMenu.textContent = nombreGuardado;

    const primeraLetra = nombreGuardado.charAt(0);

    if (letraInicial && iconoPersona) {
        letraInicial.textContent = primeraLetra; 
        iconoPersona.style.display = "none";     
        letraInicial.style.display = "flex";     
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

