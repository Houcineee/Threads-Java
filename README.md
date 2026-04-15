# Middleware Labs

Minimal Java networking exercises grouped by TP folder.

## Structure

- `tp2`: TCP client/server using Java NIO (`SocketChannel`, `Selector`), with simple message exchange and timestamp.
- `tp3`:
  - `exo1`: UDP multicast time broadcaster/listener.
  - `exo2`: Basic UDP request/response (client/server hello exchange).
  - `exo3`: UDP object transfer (`Personne`) + server-side age calculation.
- `tp4`: Java RMI exercises (hello service and remote bank account operations).
- `tp11`: TCP object serialization/deserialization (`Voiture`) between server and client.

## Quick run

From each folder, compile and run Java files, for example:

```bash
javac *.java
java Server
# in another terminal
java Client
```
