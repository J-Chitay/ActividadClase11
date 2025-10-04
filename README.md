1) Prompt de Planeación
Se definió la arquitectura MVVM + Repository + Retrofit para la app Android que consume un API REST de productos. Se propuso un árbol de paquetes y clases organizado por responsabilidades.

2) Prompt de Modelos
Se crearon las data classes Product (para los datos recibidos del backend) y ProductRequest (para enviar datos al backend al crear o actualizar). Las propiedades id y createdAt son opcionales porque el backend las genera automáticamente. Esto permite tipado seguro y manejo de nulos en Kotlin.

3) Prompt de Retrofit
Se definió la interfaz ProductApi con todos los endpoints (listAll, getById, create, update, delete, search) usando las anotaciones correctas de Retrofit.
Se creó RetrofitInstance con base URL http://10.0.2.2:8080/, GsonConverterFactory y loging en nivel BODY de OkHttp para depuración.

4) Prompt de Repository
Se creó la clase ProductRepository que expone las funciones listAll, get, create, update, delete y search.
Solo delega llamadas al RetrofitInstance.api, manteniendo la separación de responsabilidades.
Esto permite que el ViewModel pueda usar estas funciones sin depender directamente de Retrofit ni de la UI.

5) Prompt de ViewModel + State
Se creó ProductViewModel con ProductUiState usando MutableStateFlow/StateFlow.
Todas las operaciones de red (loadAll, searchByName, create, update, delete) se ejecutan con viewModelScope.launch y runCatching para manejar errores.


6) Prompt de UI Compose
Se creó ProductsScreen que contiene:
Barra de búsqueda por nombre.
Formulario de creación de producto (name, price, category).
LazyColumn con productos y botones de actualización rápida (+1.00 al precio) y eliminar.
La UI observa ProductViewModel.uiState usando collectAsStateWithLifecycle() garantizando recomposición segura.


7) Prompt de Pruebas manuales
Se definieron casos de prueba manual para CRUD y búsqueda de productos, incluyendo cómo verificar fallas de red en Logcat y en la UI. Esto asegura que la app maneja correctamente tanto resultados exitosos como errores.