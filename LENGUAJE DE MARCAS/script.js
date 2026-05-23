
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
        new usuarios(1, 'Laura Comercial', 'laura@powerbites.com', 'Ventas', 'hash123'),
        new usuarios(2, 'Pedro Distribucion', 'pedro@powerbites.com', 'Logistica', 'hash456'),
        new usuarios(3, 'Admin General', 'admin@powerbites.com', 'Administrador', 'hash789'),
        new usuarios(4, 'Sofia Ventas', 'sofia@powerbites.com', 'Ventas', 'hash321'),
        new usuarios(5, 'Javier Soporte', 'javier@powerbites.com', 'Soporte', 'hash654'),
        new usuarios(6, 'Martin Odegaard', 'martin@powerbites.com', 'Ventas', 'hash10'),
        new usuarios(7, 'Bukayo Saka', 'bukayo@powerbites.com', 'Logistica', 'hash07'),
        new usuarios(8, 'Declan Rice', 'declan@powerbites.com', 'Soporte', 'hash41'),
        new usuarios(9, 'William Saliba', 'william@powerbites.com', 'Seguridad', 'hash02'),
        new usuarios(10, 'David Raya', 'david@powerbites.com', 'Administrador', 'hash22')
    ];
};

const guardarClientesEnSesion = (listaClientes) => {
    sessionStorage.setItem('crm_clientes', JSON.stringify(listaClientes));
};

const obtenerClientesDeSesion = () => {
    const datos = sessionStorage.getItem('crm_clientes');
    return datos ? JSON.parse(datos) : [
        new clientes(1, 'Gimnasio FitLife', 'contacto@fitlife.com', '600111222', 'Calle Falsa 123'),
        new clientes(2, 'NutriSport S.L.', 'ventas@nutrisport.es', '600333444', 'Avenida Central 45'),
        new clientes(3, 'CrossFit Titan', 'info@titancf.com', '600555666', 'Nave 3, Poligono Ind.'),
        new clientes(4, 'Supermercado Sano', 'compras@sano.com', '600777888', 'Plaza Mayor 1'),
        new clientes(5, 'Carlos Runner', 'carlos@mail.com', '600999000', 'Calle Corredores 4'),
        new clientes(6, 'Emirates Fitness', 'info@emiratesfit.com', '611222333', 'London Colney 1'),
        new clientes(7, 'Gunners Nutrition', 'ventas@gunners.co.uk', '611444555', 'Highbury Square'),
        new clientes(8, 'Cannon Crossfit', 'box@cannoncf.com', '611666777', 'Arteta Avenue'),
        new clientes(9, 'North London Sports', 'nls@sports.com', '611888999', 'Islington 14'),
        new clientes(10, 'Invincibles Gym', 'invincibles@gym.com', '611000111', 'Trophy Room 49')
    ];
};

const obtenerProductosDeSesion = () => {
    const datos = sessionStorage.getItem('crm_productos');
    return datos ? JSON.parse(datos) : [
        new productos(1,'PowerBite Choco', 'Barrita 20g proteina', 2.50, 'Proteina'),
        new productos(2, 'EnergyCore', 'Avena y frutos rojos', 1.80, 'Carbohidratos'),
        new productos(3, 'VeganBite', 'Sin gluten, vegana', 2.00, 'Vegana'),
        new productos(4, 'PreWorkout', 'Con cafeina extra', 3.00, 'Pre-entreno'),
        new productos(5, 'Recovery Almond', 'Almendras y miel', 2.20, 'Recuperacion'),
        new productos(6, 'Gunner Drink', 'Bebida isotonica', 1.50, 'Hidratacion'),
        new productos(7, 'Red Cannon', 'Proteina suero', 35.00, 'Suplemento'),
        new productos(8, 'Emirates Pre', 'Explosion energia', 28.50, 'Pre-entreno'),
        new productos(9, 'North London BCAA', 'Recuperacion', 22.00, 'Recuperacion'),
        new productos(10, 'Invincibles Bar', 'Barrita oro', 3.50, 'Carbohidratos')
    ];
};


const obtenerVentasDeSesion = () => {
    const datos = sessionStorage.getItem('crm_ventas');
    return datos ? JSON.parse(datos) : [
        new ventas(1, 1, 1, '2023-10-01', 'Completado', 125.00),
        new ventas(2, 2, 4, '2023-10-02', 'Pendiente', 90.00),
        new ventas(3, 3, 1, '2023-10-03', 'Enviado', 150.00),
        new ventas(4, 4, 4, '2023-10-04', 'Completado', 44.00),
        new ventas(5, 5, 1, '2023-10-05', 'Cancelado', 20.00),
        new ventas(6, 6, 6, '2023-10-06', 'Completado', 200.00),
        new ventas(7, 7, 7, '2023-10-07', 'Enviado', 350.00),
        new ventas(8, 8, 8, '2023-10-08', 'Pendiente', 120.00),
        new ventas(9, 9, 9, '2023-10-09', 'Completado', 85.00),
        new ventas(10, 10, 10, '2023-10-10', 'Pendiente', 400.00)
    ];
};


const obtenerDetallesVentasDeSesion = () => {
    const datos = sessionStorage.getItem('crm_Detallesventas');
    return datos ? JSON.parse(datos) : [
        new detalles_venta(1, 1, 1, 50, 2.50),
        new detalles_venta(2, 2, 2, 50, 1.80),
        new detalles_venta(3, 3, 4, 50, 3.00),
        new detalles_venta(4, 4, 5, 20, 2.20),
        new detalles_venta(5, 5, 3, 10, 2.00),
        new detalles_venta(6, 6, 6, 100, 1.50),
        new detalles_venta(7, 7, 7, 10, 35.00),
        new detalles_venta(8, 8, 8, 5, 28.50),
        new detalles_venta(9, 9, 9, 4, 22.00),
        new detalles_venta(10, 10, 10, 115, 3.50)
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

