# blc-api

## Run with docker-compose
Prerequisite: Docker, docker compose

- Step 1: Navigate to code folder, pull latest code
- Step 2: Run `docker compose up -d`
- Additional step: Create account for Mailtrap.io for mail sending test, repalce your credential on docker-compose.yml file
- Public asset like avatar, document is in the MinIO public url `http://localhost:9001/block0/<path_to_file>`
#### If container doesn't run with latest code, run `docker compose up -d --build blockchain-api-app-1`


