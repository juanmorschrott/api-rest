#!/bin/bash

projectPath=$(pwd)
echo "Running backend..."
cd $projectPath/api
$(./mvnw spring-boot:run)
echo "Running frontend..."
cd $projectPath/frontend
$(ng serve)
echo "Browse to http://localhost:4200"
