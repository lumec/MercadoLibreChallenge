 # MercadoLibreLite con Clean Architecture

Esta aplicación posee una vista muy sencilla, cuya vista principal posee un campo de búsqueda ubicada en la parte superior de la app y la visualización de resultados de la misma. Además, posee una vista con información más detallada del producto seleccionado.

Es válido mencionar que este proyecto implementa los principios de **Clean Architecture** bajo el contexto Android. Para lograr este objetivo fue necesario la modularización del proyecto en combinación con MVVM; inyección de dependencias con **Hilt**; testing unitarios, instrumentacion y UI; seguimiento de errores remoto con **Timber + Firebase Crashlytics**, diseño de UI con **Jetpack Compose** y principios **SOLID**. Todo esto aprovechando la versatilidad de **Kotlin**. Por tanto, este pequeño proyecto puede ser usado como una guía para aquellos desarrolladores que deseen crear apps desacopladas, testeables, mantenibles y escalable y porque no llevarlas a producción.

Lo anterior se logró gracias a los conocimientos adquiridos en el curso de **ArchitectCoders** de **Antonio Leiva,** del cual hice parte y a otros recursos disponibles en la internet.

[Vídeo demo](https://www.youtube.com/watch?v=2bxu2ykusxQ "Vídeo demo")

------------

## Logging Remoto: Timber + Firebase Crashlytics
Por cuestiones de seguridad, el archivo `google-services.json` que hace posible que el logging remoto no se encuentra dentro del proyecto. Así que para usar esta funcionalidad dentro deberás:

1. Cambiar el nombre del paquete del proyecto.
2. Crear un proyecto de Firebase en [Firebase console](http://https://console.firebase.google.com/?authuser=0 "Firebase console").
3. Registrar la aplicación haciendo clic en el icono de Android en la sección de descripción general del proyecto.
4. Descargar el archivo `google-services.json` y colocarlo en el directorio raíz del módulo de la aplicación de Android.

[Más información](https://proandroiddev.com/remote-logging-with-timber-and-firebase-realtime-database-a9dfbe66284c "Más información")

------------

## Librerías Usadas 

#### Jetpack
- [android ktx](https://developer.android.com/kotlin/ktx "android ktx"): Es un conjunto de extensiones de Kotlin que se incluyen con Android Jetpack y otras bibliotecas de Android.
- activity: Acceso a Compose sobre la clase Activity.
- [compose](https://developer.android.com/jetpack/androidx/releases/compose?hl=en "compose"): Es un moderno kit de herramientas declarativas de IU para Android.
- [lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle "lifecycle"): realiza  acciones en respuesta a un cambio en el estado del ciclo de vida de otro componente, como actividades y fragmentos.
-[navigation-compose](https://developer.android.com/jetpack/compose/navigation " navigation-compose"): ayuda a navegar entre destinos composables. 

#### Procesos asíncronos
- [coroutines](https://kotlinlang.org/docs/coroutines-overview.html "coroutines"): perfectas para ejecución de tareas asíncronas o no bloqueantes


#### Imágenes
-  [coil](https://coil-kt.github.io/coil/ "coil"): Permite la carga de imágenes alojadas en servicios externos para Android respaldada por Kotlin Coroutines

#### Manejo de errores
- [arrow-core](https://arrow-kt.io/docs/core/ "arrow-core"): Librería funcional complementaria para el manejo de errores.

#### Inyección de dependencias
- [dagger-hilt](https://dagger.dev/hilt/ "dagger-hilt"): Proporciona una forma estándar de incorporar la inyección de dependencia de Dagger en una aplicación de Android.

#### Integración API Rest 
- [retrofit2](https://square.github.io/retrofit/ "retrofit2"): Es un cliente HTTP.
- [converter-gson](https://github.com/square/retrofit/tree/master/retrofit-converters/gson "converter-gson"): Es un convertidor que usa Gson para la serialización hacia y desde JSON.

#### Logging
- [firebase-analytics](https://firebase.google.com/products/analytics/ "firebase-analytics"): Es una herramienta de informes de fallas en tiempo real que lo ayuda a priorizar y solucionar las fallas más generalizadas en función del impacto en los usuarios reales.
- [timber](https://github.com/JakeWharton/timber "timber"): Es un logger con una API pequeña y extensible que proporciona encima de la clase Log de Android.
- [okhttp3-logging-interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor "logging-interceptor"): Un interceptor OkHttp que registra datos de solicitud y respuesta HTTP.

#### Testing
- [junit](https://junit.org/junit4/ "junit"): Es un framework de automatización para pruebas unitarias y de interfaz de usuario.
- [mockito](https://site.mockito.org/ "mockito"): Permite configurar nuestros objetos simulados para devolver algún valor específico cuando se invocan.
- [mock-web-server:](https://github.com/square/okhttp/tree/master/mockwebserver "mock-web-server:") Ayuda a simular un servidor real y escribir casos de prueba.
- [espresso](https://developer.android.com/training/testing/espresso "espresso"): Permite crear pruebas de IU automatizadas.


------------

## Capa de UI

1. Patrón de presentación: MVVM
2. Unidirectional Data Flow
3. MVVM Observables con State (Compose)
4. Estados de UI: data class
5. Eventos de UI: sealed class
6. Navegación

------------

## Capa de datos

1. Estructura y conceptos principales
2. Repositorios  y fuentes de datos
3. Single Source of Truth
4. Gestión de errores

------------

## Clean Architecture

La arquitectura limpia en Android se refiere a un enfoque para diseñar aplicaciones de manera modular y mantenible, utilizando patrones de diseño específicos para asegurar la separación de responsabilidades, facilitar el mantenimiento y actualización. La arquitectura limpia mejora la calidad del código, la facilidad de prueba y la escalabilidad de la aplicación.

<p align="center">
  <img src="https://github.com/lumec/MercadoLibreChallenge/blob/compose_testing/images/clean_architecture_es.png" height="300" width="300"/>
</p>

Este proyecto está modularizado de la siguiente manera:

- app
- data
- usecases
- domain

Además, posee dos módulos extras transversales al resto:

- testShared
- buildSrc

------------

## Inyección de dependencias

Dagger Hilt como inyector de dependencias recomendado por Google

------------

## Testing

El testShared comparte recursos para las pruebas unitarias. 
En el buildSrc se gestionan las dependencias usadas en el proyecto.

1. Pruebas unitarias de repositorios, casos de uso y viewmodels
2. Pruebas de Integración
3. Pruebas de UI con Hilt y MockWebServer. (Por el momento estos tests están presentando problemas con compose)

-------

## CI/CD

Falta implementar

-------

