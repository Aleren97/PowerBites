//clases


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

//ventas
let carritoLineas = [];

const selectCliente = document.getElementById('selectCliente');
const selectProducto = document.getElementById('selectProducto');
const inputCantidad = document.getElementById('inputCantidad');
const btnAgregarItem = document.getElementById('btnAgregarItem');
const cuerpoCarrito = document.getElementById('cuerpoCarrito');
const txtTotal = document.getElementById('txtTotal');
const btnGuardarVenta = document.getElementById('btnGuardarVenta');


const cargarClientesEnSelect = () => {
    if (!selectCliente) return;
    const clientes = obtenerClientesDeSesion();
    selectCliente.innerHTML = "";
    
    clientes.forEach(c => {
        const opt = document.createElement('option');
        opt.value = c.id;
        opt.textContent = c.nombre;
        selectCliente.appendChild(opt);
    });
};

const actualizarInterfazCarrito = () => {
    if (!cuerpoCarrito || !txtTotal) return;
    cuerpoCarrito.innerHTML = "";
    let totalAcumulado = 0;

    carritoLineas.forEach((linea, index) => {
        const subtotal = linea.cantidad * linea.precioUnitario;
        totalAcumulado += subtotal;

        const fila = document.createElement('tr');
        fila.className = "tabla-carrito__fila-item";
        fila.innerHTML = `
            <td>${linea.nombreProducto}</td>
            <td>${linea.cantidad}</td>
            <td>${linea.precioUnitario.toFixed(2)} €</td>
            <td><strong>${subtotal.toFixed(2)} €</strong></td>
            <td><button type="button" class="modulo-ventas__btn modulo-ventas__btn--peligro" data-index="${index}" style="background:#ef4444; color:white; border:none; padding:2px 8px; cursor:pointer;">X</button></td>
        `;
        cuerpoCarrito.appendChild(fila);
    });

    txtTotal.textContent = `${totalAcumulado.toFixed(2)} €`;
};

if (btnAgregarItem) {
    btnAgregarItem.addEventListener('click', () => {
        const optSeleccionada = selectProducto.options[selectProducto.selectedIndex];
        const cantidad = parseInt(inputCantidad.value);

        if (isNaN(cantidad) || cantidad < 1) {
            alert("Por favor, introduce una cantidad válida.");
            return;
        }

        let precioUnitario = parseFloat(optSeleccionada.getAttribute('data-precio'));
        if (isNaN(precioUnitario)) {
            const extraePrecio = optSeleccionada.textContent.match(/\d+(\.\d+)?/);
            precioUnitario = extraePrecio ? parseFloat(extraePrecio[0]) : 0.00;
        }

        const nombreProducto = optSeleccionada.textContent.split('(')[0].trim();

        carritoLineas.push({
            productoId: parseInt(selectProducto.value),
            nombreProducto: nombreProducto,
            precioUnitario: precioUnitario,
            cantidad: cantidad
        });

        actualizarInterfazCarrito();
        inputCantidad.value = 1;
    });
}

if (cuerpoCarrito) {
    cuerpoCarrito.addEventListener('click', (evento) => {
        if (evento.target.classList.contains('modulo-ventas__btn--peligro')) {
            const index = parseInt(evento.target.getAttribute('data-index'));
            carritoLineas.splice(index, 1);
            actualizarInterfazCarrito();
        }
    });
}

if (btnGuardarVenta) {
    btnGuardarVenta.addEventListener('click', () => {
        if (carritoLineas.length === 0) {
            alert("Debes añadir al menos una línea de barritas energéticas a la venta.");
            return;
        }
        alert("¡Venta guardada en la sesión con éxito!");
        carritoLineas = [];
        actualizarInterfazCarrito();
    });
}

document.addEventListener("DOMContentLoaded", () => {
    cargarClientesEnSelect();
    
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
});



//Productos

 let listaProductos = [];

const catalogContainer = document.getElementById('catalog-container');
const productModal = document.getElementById('product-modal');
const productForm = document.getElementById('product-form');
const btnAddProduct = document.getElementById('btn-add-product');
const btnCloseModal = document.getElementById('btn-close-modal');
const modalTitle = document.getElementById('modal-title');

const guardarEnSessionStorage = () => {
    sessionStorage.setItem('crm_productos', JSON.stringify(listaProductos));
};

function inicializarApp() {
    listaProductos = obtenerProductosDeSesion();
    
    if (!sessionStorage.getItem('crm_productos')) {
        guardarEnSessionStorage();
    }
    
    pintarCatalogo();
}

const pintarCatalogo = () => {
    if (!catalogContainer) return; 
    catalogContainer.innerHTML = '';

    if (listaProductos.length === 0) {
        catalogContainer.innerHTML = `<p class="page-header__subtitle">No hay barritas en el catálogo. ¡Añade una nueva!</p>`;
        return;
    }

    listaProductos.forEach(producto => {
        const tarjeta = document.createElement('article');
        tarjeta.className = 'product-card';
        
        tarjeta.innerHTML = `
            <span class="product-card__badge">${producto.categoria}</span>
            <h2 class="product-card__title">${producto.nombre}</h2>
            <p class="product-card__description">${producto.descripcion}</p>
            <div class="product-card__price">${parseFloat(producto.precio).toFixed(2)} €</div>
            <div class="product-card__actions">
                <button class="btn--secondary" onclick="prepararEdicion(${producto.id})">Editar</button>
                <button class="btn--danger" onclick="eliminarProducto(${producto.id})">Borrar</button>
            </div>
        `;
        catalogContainer.appendChild(tarjeta);
    });
};

const procesarOperacionProducto = (mensajeExito, callbackAccion) => {
    callbackAccion(); 
    guardarEnSessionStorage();
    pintarCatalogo();
    cerrarModal();
};

if(productForm) {
    productForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const id = document.getElementById('product-id').value;
        const nombre = document.getElementById('prod-name').value;
        const descripcion = document.getElementById('prod-desc').value;
        const categoria = document.getElementById('prod-cate').value;
        const precio = document.getElementById('prod-price').value;

        if (id) {
            procesarOperacionProducto("Producto editado correctamente", () => {
                const indice = listaProductos.findIndex(p => p.id === parseInt(id));
                if (indice !== -1) {
                    listaProductos[indice] = new productos(parseInt(id), nombre, descripcion, precio, categoria);
                }
            });
        } else {
            procesarOperacionProducto("Nuevo producto creado", () => {
                const nuevoId = listaProductos.length > 0 ? Math.max(...listaProductos.map(p => p.id)) + 1 : 1;
                listaProductos.push(new productos(nuevoId, nombre, descripcion, precio, categoria));
            });
        }
    });
}

window.prepararEdicion = (id) => {
    const producto = listaProductos.find(p => p.id === id);
    if (!producto) return;

    document.getElementById('product-id').value = producto.id;
    document.getElementById('prod-name').value = producto.nombre;
    document.getElementById('prod-desc').value = producto.descripcion;
    document.getElementById('prod-cate').value = producto.categoria;
    document.getElementById('prod-price').value = producto.precio;

    if(modalTitle) modalTitle.textContent = "Editar Barrita Energética";
    abrirModal();
};

window.eliminarProducto = (id) => {
    if (confirm("¿Estás seguro de que deseas eliminar esta barrita?")) {
        procesarOperacionProducto("Producto eliminado", () => {
            listaProductos = listaProductos.filter(p => p.id !== id);
        });
    }
};

const abrirModal = () => productModal?.classList.add('modal--open');
const cerrarModal = () => {
    productModal?.classList.remove('modal--open');
    productForm?.reset();
    const idOculto = document.getElementById('product-id');
    if(idOculto) idOculto.value = '';
    if(modalTitle) modalTitle.textContent = "Nueva Barrita Energética";
};

if(btnAddProduct) btnAddProduct.addEventListener('click', abrirModal);
if(btnCloseModal) btnCloseModal.addEventListener('click', cerrarModal);

inicializarApp();


//Clientes


const formClienteDirecto = document.getElementById('clienteFormDirecto');
const selectClienteConsulta = document.getElementById('selectClienteConsulta');
const btnVerFicha = document.getElementById('btn--VerFicha');
const apartadoFichaCliente = document.getElementById('apartadoFichaCliente');
const cuerpoFichaCliente = document.getElementById('cuerpoFichaCliente');
const fichaNombre = document.getElementById('ficha-name');


const actualizarSelectClientes = () => {
    if (!selectClienteConsulta) return;
    
    const clientesActuales = obtenerClientesDeSesion(); 
    selectClienteConsulta.innerHTML = "";

    clientesActuales.forEach(c => {
        const opt = document.createElement('option');
        opt.value = c.id;
        opt.textContent = c.nombre;
        selectClienteConsulta.appendChild(opt);
    });
};


if (formClienteDirecto) {
    formClienteDirecto.addEventListener('submit', (e) => {
        e.preventDefault();

        const nombre = document.getElementById('client-name').value.trim();
        const email = document.getElementById('client-email').value.trim();
        const telefono = document.getElementById('client-phone').value.trim();
        const direccion = document.getElementById('client-direction').value.trim();

        const listaActual = obtenerClientesDeSesion();

        const nuevoId = listaActual.length > 0 ? Math.max(...listaActual.map(c => c.id)) + 1 : 1;

        const nuevoCliente = new clientes(nuevoId, nombre, email, telefono, direccion);
        listaActual.push(nuevoCliente);

        guardarClientesEnSesion(listaActual);

        actualizarSelectClientes();
        formClienteDirecto.reset();

        alert(`¡Cliente "${nombre}" registrado con éxito y añadido al apartado de consulta!`);
    });
}


if (btnVerFicha) {
    btnVerFicha.addEventListener('click', () => {
        if (!selectClienteConsulta || selectClienteConsulta.options.length === 0) {
            alert("No hay ningún cliente registrado en el sistema.");
            return;
        }

        const idSeleccionado = parseInt(selectClienteConsulta.value);
        const listaActual = obtenerClientesDeSesion();
        const clienteEncontrado = listaActual.find(c => c.id === idSeleccionado);

        if (clienteEncontrado && apartadoFichaCliente && cuerpoFichaCliente) {

            fichaNombre.textContent = clienteEncontrado.nombre;
            
            cuerpoFichaCliente.innerHTML = `
                <tr>
                    <td><strong>ID Único del Sistema:</strong></td>
                    <td>#000${clienteEncontrado.id}</td>
                </tr>
                <tr>
                    <td><strong>Correo Electrónico:</strong></td>
                    <td>${clienteEncontrado.email}</td>
                </tr>
                <tr>
                    <td><strong>Teléfono de Contacto:</strong></td>
                    <td>${clienteEncontrado.telefono}</td>
                </tr>
                <tr>
                    <td><strong>Dirección Registrada:</strong></td>
                    <td>${clienteEncontrado.direccion}</td>
                </tr>
            `;

            apartadoFichaCliente.style.display = "block";
        }
    });
}

const btnBorrarCliente = document.getElementById('btn--BorrarCliente');


if (btnBorrarCliente) {
    btnBorrarCliente.addEventListener('click', () => {
        if (!selectClienteConsulta || selectClienteConsulta.options.length === 0) {
            alert("No hay ningún cliente para eliminar.");
            return;
        }

        const idSeleccionado = parseInt(selectClienteConsulta.value);
        const listaActual = obtenerClientesDeSesion();
        const clienteEncontrado = listaActual.find(c => c.id === idSeleccionado);

        if (!clienteEncontrado) return;

        if (confirm(`¿Estás seguro de que deseas eliminar permanentemente a "${clienteEncontrado.nombre}" del sistema?`)) {
            
            const listaFiltrada = listaActual.filter(c => c.id !== idSeleccionado);
            
            guardarClientesEnSesion(listaFiltrada);
            
            actualizarSelectClientes();
            
            if (apartadoFichaCliente) {
                apartadoFichaCliente.style.display = "none";
            }
            
            alert("Cliente eliminado correctamente del almacenamiento local.");
        }
    });
}
document.addEventListener("DOMContentLoaded", () => {
    if (selectClienteConsulta) {
        actualizarSelectClientes();
    }
});

//Usuarios

const formUsuarioDirecto = document.getElementById('usuarioFormDirecto');
const selectUsuarioConsulta = document.getElementById('selectUsuarioConsulta');
const btnVerFichaUsuario = document.getElementById('btnVerFichaUsuario');
const btnBorrarUsuarioConsulta = document.getElementById('btnBorrarUsuarioConsulta');
const apartadoFichaUsuario = document.getElementById('apartadoFichaUsuario');
const cuerpoFichaUsuario = document.getElementById('cuerpoFichaUsuario');
const fichaUsuarioNombre = document.getElementById('fichaUsuarioNombre');

const actualizarSelectUsuarios = () => {
    if (!selectUsuarioConsulta) return;
    
    const usuariosActuales = obtenerUsuariosDeSesion(); 
    selectUsuarioConsulta.innerHTML = "";

    usuariosActuales.forEach(u => {
        const opt = document.createElement('option');
        opt.value = u.id;
        opt.textContent = `${u.nombre} (${u.rol})`;
        selectUsuarioConsulta.appendChild(opt);
    });
};


const guardarUsuariosEnSesion = (listaUsuarios) => {
    sessionStorage.setItem('crm_usuarios', JSON.stringify(listaUsuarios));
};

if (formUsuarioDirecto) {
    formUsuarioDirecto.addEventListener('submit', (e) => {
        e.preventDefault();

        const nombre = document.getElementById('usrNombre').value.trim();
        const email = document.getElementById('usrEmail').value.trim();
        const rol = document.getElementById('usrRol').value;
        const password = document.getElementById('usrPassword').value;

        const listaActual = obtenerUsuariosDeSesion();

        const nuevoId = listaActual.length > 0 ? Math.max(...listaActual.map(u => u.id)) + 1 : 1;

        const passwordHash = btoa(password); 

        const nuevoUsuario = new usuarios(nuevoId, nombre, email, rol, passwordHash);
        listaActual.push(nuevoUsuario);

        guardarUsuariosEnSesion(listaActual);

        actualizarSelectUsuarios();
        formUsuarioDirecto.reset();

        alert(`¡Usuario "${nombre}" registrado con éxito en el equipo de trabajo!`);
    });
}

if (btnVerFichaUsuario) {
    btnVerFichaUsuario.addEventListener('click', () => {
        if (!selectUsuarioConsulta || selectUsuarioConsulta.options.length === 0) {
            alert("No hay usuarios registrados en el equipo.");
            return;
        }

        const idSeleccionado = parseInt(selectUsuarioConsulta.value);
        const listaActual = obtenerUsuariosDeSesion();
        const usuarioEncontrado = listaActual.find(u => u.id === idSeleccionado);

        if (usuarioEncontrado && apartadoFichaUsuario && cuerpoFichaUsuario) {
            fichaUsuarioNombre.textContent = usuarioEncontrado.nombre;
            
            cuerpoFichaUsuario.innerHTML = `
                <tr>
                    <td><strong>ID de Empleado:</strong></td>
                    <td>#USR00${usuarioEncontrado.id}</td>
                </tr>
                <tr>
                    <td><strong>Email Corporativo:</strong></td>
                    <td>${usuarioEncontrado.email}</td>
                </tr>
                <tr>
                    <td><strong>Rol de Acceso / Permisos:</strong></td>
                    <td><span style="background: #edf2f7; padding: 4px 8px; border-radius: 4px; font-weight: bold; color: #2b6cb0;">${usuarioEncontrado.rol}</span></td>
                </tr>
                <tr>
                    <td><strong>Hash de Contraseña (Encriptado):</strong></td>
                    <td style="font-family: monospace; color: #718096; font-size: 0.85rem;">${usuarioEncontrado.password_hash || "Sin hash asignado"}</td>
                </tr>
            `;

            apartadoFichaUsuario.style.display = "block";
        }
    });
}


if (btnBorrarUsuarioConsulta) {
    btnBorrarUsuarioConsulta.addEventListener('click', () => {
        if (!selectUsuarioConsulta || selectUsuarioConsulta.options.length === 0) {
            alert("No hay perfiles para eliminar.");
            return;
        }

        const idSeleccionado = parseInt(selectUsuarioConsulta.value);
        const listaActual = obtenerUsuariosDeSesion();
        const usuarioEncontrado = listaActual.find(u => u.id === idSeleccionado);

        if (!usuarioEncontrado) return;

        if (confirm(`¿Estás seguro de que deseas revocar el acceso y dar de baja a "${usuarioEncontrado.nombre}"?`)) {
            const listaFiltrada = listaActual.filter(u => u.id !== idSeleccionado);
            
            guardarUsuariosEnSesion(listaFiltrada);
            actualizarSelectUsuarios();
            
            if (apartadoFichaUsuario) {
                apartadoFichaUsuario.style.display = "none";
            }
            
            alert("El usuario ha sido eliminado del sistema de cuentas.");
        }
    });
}

document.addEventListener("DOMContentLoaded", () => {
    if (selectUsuarioConsulta) {
        actualizarSelectUsuarios();
    }
});