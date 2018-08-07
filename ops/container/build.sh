#!/bin/bash
docker build -t travelbud-api -f Dockerfile .
docker run --name travelBudDB
docker rm -v ${imageName}