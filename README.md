# BinarioEducativo

Una aplicación educativa Android para enseñar a niños de primaria conceptos del sistema binario y conversiones numéricas de manera interactiva y divertida.

## 📱 Descripción

BinarioEducativo es una herramienta educativa diseñada para introducir a niños de primaria en el mundo de la representación binaria de la información. La aplicación utiliza una interfaz amigable y juegos interactivos para enseñar conceptos como:

- Sistema binario (0s y 1s)
- Conversiones entre sistemas decimal y binario
- Representación numérica a través de interruptores binarios

## ✨ Características

- **Sección educativa**: Explicaciones claras y visuales sobre el sistema binario y conversiones
- **Juegos interactivos**: 
  - Conversor Binario: Convierte números entre decimal y binario
  - Interruptores Binarios: Usa interruptores para formar números binarios
- **Temas personalizables**:
  - Tema Guinda (IPN)
  - Tema Azul (ESCOM)
- **Modo claro/oscuro**: Adaptación automática según las preferencias del sistema
- **Diseño responsivo**: Adaptable a diferentes tamaños de pantalla


## 🔧 Tecnologías utilizadas

- Kotlin
- Android Jetpack
- ViewPager2 con TabLayout
- Material Design Components
- SharedPreferences para almacenamiento de preferencias

## 🚀 Instalación

1. Clona este repositorio:
```
git clone https://github.com/GioSENPAII/Primer_Examen_Parcial_APPS.git
```

2. Abre el proyecto en Android Studio.

3. Ejecuta la aplicación en un dispositivo o emulador con API level 24 (Android 7.0) o superior.

## 📋 Requisitos

- Android 7.0 (API level 24) o superior
- Android Studio Arctic Fox o superior
- Gradle 8.10.2 o superior

## 🏗️ Estructura del proyecto

- **MainActivity**: Pantalla principal con opciones para navegar a aprendizaje, juegos y configuración
- **LearningActivity**: Contenido educativo organizado en pestañas:
  - BinaryInfoFragment: Información sobre el sistema binario
  - ConversionFragment: Tutoriales sobre conversiones entre sistemas
- **GamesActivity**: Selección de juegos educativos
  - BinaryConverterGameActivity: Juego de conversión entre sistemas
  - BinarySwitchGameActivity: Juego de interruptores binarios
- **ThemeManager**: Sistema de gestión de temas personalizables

## 👨‍💻 Decisiones de diseño

- **Interfaz amigable para niños**: Botones grandes, colores vivos y retroalimentación clara
- **Progresión educativa**: Desde conceptos básicos hasta aplicaciones prácticas
- **Temas personalizables**: Permite al usuario elegir su experiencia visual
- **Separación de responsabilidades**: Arquitectura clara con Activities y Fragments específicos

## 📝 Contacto

Giovanni Javier Longoria Bunoust - glongoria.3a.is@gmail.com

Enlace del proyecto: https://github.com/GioSENPAII/Primer_Examen_Parcial_APPS.git

## 📄 Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

## 🙏 Agradecimientos

- IPN y ESCOM por la inspiración en los temas
- A mi profesor de la materia de Desarrollo de Aplicaciones Móviles Nativas
