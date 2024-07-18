# Spring Security 6 y JWT

## Introducción

Este proyecto demuestra cómo configurar Spring Security 6 con JSON Web Tokens (JWT) para un sistema de seguridad web robusto y escalable.

## Arquitectura de Spring Security

Spring Security es un marco poderoso y altamente personalizable para la autenticación y autorización en aplicaciones Java. Su arquitectura está compuesta por varios componentes clave:

### Componentes Principales

1. **AuthenticationManager**: Gestiona la autenticación. Se encarga de validar las credenciales del usuario.
2. **SecurityContext**: Almacena detalles de seguridad como la autenticación actual del usuario.
3. **SecurityContextHolder**: Proporciona acceso al `SecurityContext`.
4. **UserDetailsService**: Carga datos específicos del usuario.
5. **PasswordEncoder**: Codifica y verifica contraseñas.
6. **FilterChainProxy**: Proporciona una cadena de filtros de seguridad.
7. **AuthenticationProvider**: Proceso de autenticación, a menudo delegando a `UserDetailsService`.

## Funcionamiento de los Componentes

1. **Solicitud de Autenticación**:
   - El cliente envía una solicitud de inicio de sesión con credenciales.
   - Un filtro de autenticación captura la solicitud.

2. **Validación de Credenciales**:
   - `AuthenticationManager` delega a `AuthenticationProvider` para la autenticación.
   - `AuthenticationProvider` utiliza `UserDetailsService` para cargar detalles del usuario.
   - `PasswordEncoder` verifica la contraseña.

3. **Generación de JWT**:
   - Tras una autenticación exitosa, se genera un JWT.
   - El JWT se envía al cliente.

4. **Autorización de Solicitudes**:
   - Para solicitudes posteriores, el cliente envía el JWT en el encabezado de autorización.
   - Un filtro de JWT captura la solicitud y valida el token.
   - Si el token es válido, se establece el contexto de seguridad.
