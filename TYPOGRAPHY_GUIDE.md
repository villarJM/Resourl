# Guía de Tipografía - Material Design 3

## Familias de Fuentes

### Outfit
Fuente para texto de contenido, párrafos, botones y etiquetas.
- Pesos disponibles: Thin, ExtraLight, Light, Regular, Medium, SemiBold, Bold, ExtraBold, Black

### Funnel Display
Fuente para títulos grandes y encabezados destacados.
- Pesos disponibles: Light, Regular, Medium, SemiBold, Bold, ExtraBold

---

## Estilos de Tipografía Material Design 3

### Display Styles (Funnel Display)
**Uso:** Títulos muy grandes, pantallas de bienvenida, hero sections

```kotlin
// displayLarge - 57sp
Text("Título Principal", style = MaterialTheme.typography.displayLarge)

// displayMedium - 45sp
Text("Título Grande", style = MaterialTheme.typography.displayMedium)

// displaySmall - 36sp
Text("Título Mediano", style = MaterialTheme.typography.displaySmall)
```

---

### Headline Styles (Funnel Display + Outfit)
**Uso:** Encabezados de secciones, títulos de pantallas

```kotlin
// headlineLarge - 32sp (Funnel Display)
Text("Sección Principal", style = MaterialTheme.typography.headlineLarge)

// headlineMedium - 28sp (Funnel Display)
Text("Subsección", style = MaterialTheme.typography.headlineMedium)

// headlineSmall - 24sp (Outfit)
Text("Título de Sección", style = MaterialTheme.typography.headlineSmall)
```

---

### Title Styles (Outfit)
**Uso:** Títulos de tarjetas, diálogos, AppBar

```kotlin
// titleLarge - 22sp
Text("Título de Card", style = MaterialTheme.typography.titleLarge)

// titleMedium - 16sp
Text("Título de Diálogo", style = MaterialTheme.typography.titleMedium)

// titleSmall - 14sp
Text("Título Pequeño", style = MaterialTheme.typography.titleSmall)
```

---

### Body Styles (Outfit)
**Uso:** Contenido principal, párrafos, descripciones

```kotlin
// bodyLarge - 16sp
Text("Texto de contenido principal...", style = MaterialTheme.typography.bodyLarge)

// bodyMedium - 14sp
Text("Descripción o texto secundario...", style = MaterialTheme.typography.bodyMedium)

// bodySmall - 12sp
Text("Texto auxiliar o notas...", style = MaterialTheme.typography.bodySmall)
```

---

### Label Styles (Outfit)
**Uso:** Botones, chips, etiquetas, tabs

```kotlin
// labelLarge - 14sp
Button(onClick = {}) {
    Text("Botón", style = MaterialTheme.typography.labelLarge)
}

// labelMedium - 12sp
Text("Etiqueta", style = MaterialTheme.typography.labelMedium)

// labelSmall - 11sp
Text("Chip o Tag", style = MaterialTheme.typography.labelSmall)
```

---

## Ejemplos de Uso por Componente

### Pantalla de Login
```kotlin
Column {
    // Título principal
    Text("Bienvenido", style = MaterialTheme.typography.displayMedium)
    
    // Subtítulo
    Text("Inicia sesión para continuar", style = MaterialTheme.typography.bodyLarge)
    
    // Labels de inputs
    Text("Email", style = MaterialTheme.typography.labelMedium)
    
    // Botón
    Button(onClick = {}) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.labelLarge)
    }
}
```

### Tarjeta de Contenido
```kotlin
Card {
    Column {
        // Título de la tarjeta
        Text("Título", style = MaterialTheme.typography.titleLarge)
        
        // Descripción
        Text("Contenido...", style = MaterialTheme.typography.bodyMedium)
        
        // Metadata
        Text("Fecha: 3 Nov 2025", style = MaterialTheme.typography.bodySmall)
    }
}
```

### Lista con Items
```kotlin
LazyColumn {
    items(list) { item ->
        ListItem(
            headlineContent = {
                // Título del item
                Text(item.title, style = MaterialTheme.typography.titleMedium)
            },
            supportingContent = {
                // Subtítulo
                Text(item.subtitle, style = MaterialTheme.typography.bodySmall)
            }
        )
    }
}
```

---

## Especificaciones Técnicas

| Estilo | Fuente | Peso | Tamaño | Altura Línea | Espaciado |
|--------|--------|------|--------|--------------|-----------|
| displayLarge | Funnel Display | Bold | 57sp | 64sp | -0.25sp |
| displayMedium | Funnel Display | Bold | 45sp | 52sp | 0sp |
| displaySmall | Funnel Display | SemiBold | 36sp | 44sp | 0sp |
| headlineLarge | Funnel Display | SemiBold | 32sp | 40sp | 0sp |
| headlineMedium | Funnel Display | Medium | 28sp | 36sp | 0sp |
| headlineSmall | Outfit | SemiBold | 24sp | 32sp | 0sp |
| titleLarge | Outfit | SemiBold | 22sp | 28sp | 0sp |
| titleMedium | Outfit | Medium | 16sp | 24sp | 0.15sp |
| titleSmall | Outfit | Medium | 14sp | 20sp | 0.1sp |
| bodyLarge | Outfit | Regular | 16sp | 24sp | 0.5sp |
| bodyMedium | Outfit | Regular | 14sp | 20sp | 0.25sp |
| bodySmall | Outfit | Regular | 12sp | 16sp | 0.4sp |
| labelLarge | Outfit | Medium | 14sp | 20sp | 0.1sp |
| labelMedium | Outfit | Medium | 12sp | 16sp | 0.5sp |
| labelSmall | Outfit | Medium | 11sp | 16sp | 0.5sp |

