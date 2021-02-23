### Marvel

Arquitectura MVP usada por mayor simplicidad y reutilización de código.

### Deeplink

He creado una estructura de depplink para poder usar la llamada de 

`/v1/public/characters/{id}`

marveldiego://marvelApp?id=1010354

##Test

Solo hay parte de test unitario y de integración. De interfaz con Espresso no he realizado.

##Proguard

No he puesto reglas de proguard

##Librerias
Coroutines para la gestión de hilos.

Material y constrainLayout para el diseño de la interfaz.

He usado las librerias de Retrofit para las peticiones.

Serialización de JSON con Serialization de jetbrains.

Dagger Hilt para la inyección de dependencias ya que es mucho mas comodo que dagger solo.

Glide para la carga de imagenes.

Y para testing Mockito MockWebServer JUnit y Roboeletric.