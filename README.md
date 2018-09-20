# loadtest4j-example-scala

A working example of [loadtest4j](https://www.loadtest4j.org) in Scala and SBT.

## Usage

### Normal

```bash
sbt test                            # Run unit tests

sbt it:test                         # Run integration tests

sbt lt:test                         # Run load tests (in the default environment)

sbt "profile staging" lt:test       # Run load tests (in the 'staging' environment)

sbt "profile production" lt:test    # Run load tests (in the 'production' environment)
```

### SBT Shell

```bash
sbt shell
>
> test                # Run unit tests
>
> it:test             # Run integration tests
>
> lt:test             # Run load tests (in the default environment)
>
> profile staging     # Activate 'staging' environment
> lt:test             # Run load tests (in the 'staging' environment)
>
> profile production  # Activate 'production' environment
> lt:test             # Run load tests (in the 'production' environment)
>
> reload              # Revert to the default environment
>
> lt:test             # Run load tests (in the default environment)
```

## Caveats

- **SBT does not currently support resource filtering.** This means that Maven-style `foo = ${bar}` 
property interpolation is not available. As a consequence, loadtest4j config cannot be specified in
`loadtest4j.properties`, instead it must be set as JVM system properties in `build.sbt`.
- **IntelliJ IDEA sometimes forgets that `src/lt/scala` is a test source directory.** If this occurs
you can fix it by re-marking `src/lt/scala` as a test source directory.
- **You must run load tests from the SBT shell or command line.** This is due to two missing 
features in IntelliJ IDEA:
  - IntelliJ IDEA does not currently support SBT Configurations (to pick up loadtest4j custom
  environments).  
  - IntelliJ IDEA does not currently read `testOptions` from build.sbt (to pick up any loadtest4j
  config that is defined as a JVM property in build.sbt).