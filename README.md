<div align="center">

  <h1>GitSpace</h1>

  <p>
    This application is part of the teamspace platform built on a microservice architecture.
  </p>

<!-- Badges -->
<p>
    <a href="https://github.com/sulsoltanoff/teamspace-gitspace/graphs/contributors">
        <img src="https://img.shields.io/github/contributors/sulsoltanoff/teamspace-gitspace" alt="contributors" />
    </a>
    <a href="">
        <img src="https://img.shields.io/github/last-commit/sulsoltanoff/teamspace-gitspace" alt="last update" />
    </a>
    <a href="https://github.com/sulsoltanoff/awesome-readme-template/issues/">
        <img src="https://img.shields.io/github/issues/sulsoltanoff/teamspace-gitspace" alt="open issues" />
    </a>
    <a href="">
        <img src="https://img.shields.io/github/actions/workflow/status/sulsoltanoff/teamspace-gitspace/codeql.yml" />
    </a>
    <a>
        <img src="https://img.shields.io/github/v/tag/sulsoltanoff/teamspace-gitspace?include_prereleases&sort=semver" />
    </a>
    <a>
        <img src="https://img.shields.io/github/languages/code-size/sulsoltanoff/teamspace-gitspace" />
    </a>
    <a href="https://github.com/sulsoltanoff/teamspace-gitspace/blob/main/LICENSE">
        <img src="https://img.shields.io/github/license/sulsoltanoff/teamspace-gitspace.svg" alt="license" />
    </a>
</p>

<h4>
    <a href="https://github.com/sulsoltanoff/teamspace-gitspace">Documentation</a>
  <span> · </span>
    <a href="https://github.com/sulsoltanoff/ateamspace-gitspace/issues/">Report Bug</a>
  <span> · </span>
    <a href="https://github.com/sulsoltanoff/teamspace-gitspace/issues/">Request Feature</a>
  </h4>
</div>

<br />
GitSpace provides tools for automating the software development process, such as an integrated development environment, version control support, tools for building and testing automation. 
It empowers developers and teams to work more efficiently, increasing productivity and giving them the tools to accelerate development.

<!-- Table of Contents -->

# :memo: Table of Contents

- [About the Project](#-star2--about-the-project)
  - [Project structure](#project-structure)
  - [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#gear-installation)
  - [Running Tests](#running-tests)
  - [Run Locally](#running-run-locally)
  - [Deployment](#triangular_flag_on_post-deployment)
- [Usage](#eyes-usage)
- [Roadmap](#compass-roadmap)
- [Contributing](#wave-contributing)
  - [Code of Conduct](#scroll-code-of-conduct)
- [FAQ](#grey_question-faq)
- [License](#warning-license)
- [Contact](#handshake-contact)
- [Acknowledgements](#gem-acknowledgements)

## :star2: About the Project

### Project Structure

Node is required for generation and recommended for development. `package.json` is always generated for a better development experience with prettier, commit hooks, scripts and so on.

`/src/*` structure follows default Java structure.

- `.yo-rc.json` - Yeoman configuration file
  JHipster configuration is stored in this file at `generator-jhipster` key. You may find `generator-jhipster-*` for specific blueprints configuration.
- `.yo-resolve` (optional) - Yeoman conflict resolver
  Allows to use a specific action when conflicts are found skipping prompts for files that matches a pattern. Each line should match `[pattern] [action]` with pattern been a [Minimatch](https://github.com/isaacs/minimatch#minimatch) pattern and action been one of skip (default if ommited) or force. Lines starting with `#` are considered comments and are ignored.
- `/src/main/docker` - Docker configurations for the application and services that the application depends on

### Tech Stack

<details>
  <summary>Technologies and utils using in app</summary>
  <ul>
    <li><a href="https://bell-sw.com/libericajdk/">JDK 17 Liberica</a></li>
    <li><a href="https://spring.io">Spring</a></li>
    <li><a href="https://www.postgresql.org/">Postgresql</a></li>
    <li><a href="https://maven.apache.org/">Maven</a></li>
    <li><a href="https://www.docker.com/">Docker</a></li>
    <li><a href="https://nodejs.org">NodeJS for dev</a></li>
    <li><a href="https://kafka.apache.org/">Apache kafka</a></li>    
    <li><a href="https://grafana.com/">Grafana</a></li>
    <li><a href="https://prometheus.io/">Prometheus</a></li>
    <li><a href="https://www.elastic.co/">Elasticsearch</a></li>
    <li><a href="https://www.sonarsource.com/products/sonarqube/">Sonar Qube</a></li>
    <li><a href="https://hibernate.org/">Hibernate</a></li>
  </ul>
</details>

## Getting Started

### Prerequisites

This project uses Liberica JDK and NodeJs (Only development) platform.

- Install Liberica JDK 17 [Docs for install](https://bell-sw.com/pages/liberica_install_guide-17.0.5/)
- Install NodeJS v16.x. We recommend use [nvm](https://github.com/nvm-sh/nvm) package manager
- Docker and docker-compose
- Git
- Eclipse or IntelliJ IDEA

### Installation

Follow the commands for installation:

```bash
./mvnw clean validate compile
```

```bash
npm i
```

Optional step for settings idea

```bash
./mvnw idea:idea ### For IntelliJ IDEA

./mvnw eclipse:eclipse ### For Eclipse
```

To start your application in the dev profile, run:

```bash
./mvnw

## Or use npm
npm run app:start
```

### Running Tests

For run Unit test

```bash
npm run backend:unit:test
npm run ci:backend:test
./mvnw verify
```

### Doing API-First development using openapi-generator-cli

[OpenAPI-Generator]() is configured for this application. You can generate API code from the `src/main/resources/swagger/api.yml` definition file by running:

```bash
./mvnw generate-sources
```

Then implements the generated delegate classes with `@Service` classes.

To edit the `api.yml` definition file, you can use a tool such as [Swagger-Editor](). Start a local instance of the swagger-editor using docker by running: `docker-compose -f src/main/docker/swagger-editor.yml up -d`. The editor will then be reachable at [http://localhost:7742](http://localhost:7742).

Refer to [Doing API-First development][] for more details.

## Building for production

### Packaging as jar

To build the final jar and optimize the GitSpace application for production, run:

```
./mvnw -Pprod clean verify
```

To ensure everything worked, run:

```
java -jar target/*.jar
```

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```
./mvnw -Pprod,war clean verify
```

## Testing

To launch your application's tests, run:

```
./mvnw verify
```

### Other tests

Performance tests are run by [Gatling][] and written in Scala. They're located in [src/test/gatling](src/test/gatling).

To use those tests, you must install Gatling from [https://gatling.io/](https://gatling.io/).

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

Note: we have turned off authentication in [src/main/docker/sonar.yml](src/main/docker/sonar.yml) for out of the box experience while trying out SonarQube, for real use cases turn it back on.

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a postgresql database in a docker container, run:

```
docker-compose -f src/main/docker/postgresql.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/postgresql.yml down
```

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

```
npm run java:docker
```

Or build a arm64 docker image when using an arm64 processor os like MacOS with M1 processor family running:

```
npm run java:docker:arm64
```

Then run:

```
docker-compose -f src/main/docker/app.yml up -d
```

When running Docker Desktop on MacOS Big Sur or later, consider enabling experimental `Use the new Virtualization framework` for better processing performance ([disk access performance is worse](https://github.com/docker/roadmap/issues/7)).

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 7.9.3 archive]: https://www.jhipster.tech/documentation-archive/v7.9.3
[doing microservices with jhipster]: https://www.jhipster.tech/documentation-archive/v7.9.3/microservices-architecture/
[using jhipster in development]: https://www.jhipster.tech/documentation-archive/v7.9.3/development/
[service discovery and configuration with the jhipster-registry]: https://www.jhipster.tech/documentation-archive/v7.9.3/microservices-architecture/#jhipster-registry
[using docker and docker-compose]: https://www.jhipster.tech/documentation-archive/v7.9.3/docker-compose
[using jhipster in production]: https://www.jhipster.tech/documentation-archive/v7.9.3/production/
[running tests page]: https://www.jhipster.tech/documentation-archive/v7.9.3/running-tests/
[code quality page]: https://www.jhipster.tech/documentation-archive/v7.9.3/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/documentation-archive/v7.9.3/setting-up-ci/
[node.js]: https://nodejs.org/
[npm]: https://www.npmjs.com/
[gatling]: https://gatling.io/
[openapi-generator]: https://openapi-generator.tech
[swagger-editor]: https://editor.swagger.io
[doing api-first development]: https://www.jhipster.tech/documentation-archive/v7.9.3/doing-api-first-development/
