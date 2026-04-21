# Middleware Labs

Java middleware exercises grouped by TP folder, covering sockets, UDP, Java NIO, RMI, and basic concurrent/distributed service patterns.

## Structure

- `tp1`: basic TCP client/server exchange with a threaded server and shared socket utility classes.
- `tp2`: TCP client/server using Java NIO (`ServerSocketChannel`, `SocketChannel`, `Selector`) with non-blocking I/O and timestamped responses.
- `tp3`:
  - `exo1`: UDP multicast broadcaster/listener.
  - `exo2`: basic UDP request/response between client and server.
  - `exo3`: UDP object transfer using `Personne` with server-side processing.
- `tp4`: Java RMI exercises.
  - `HelloServer`: simple remote hello service.
  - `application`: RMI client-side application classes.
  - `exo2`: remote bank account service (`Compte`) with server and client code.
  - `exo3`: extended bank example with concurrent client operations.
- `tp5`:
  - `exo1`: synchronous e-commerce simulation using `ExecutorService` and `Future`.
  - `exo2`: distributed bank transfer example split into `accountServer`, `TransfertService`, `client`, and shared `common` interfaces/exceptions.
- `chatServer`: RMI chat application with separate `server` and `client` packages.

## Notes

- `tp5/exo2/exo2.zip` is included alongside the source tree.

## Quick Run

Compile and run from the relevant folder, for example:

```bash
javac *.java
java Server
```

For client/server exercises, start the server first, then run the client from another terminal when needed.
