#bin/bash

ARTIFACT=py-mov
VERSION=0.0.1

docker buildx build -t ${ARTIFACT}:${VERSION} .