#!/bin/bash
set -e

echo "🧪 Build Android / KMP"
./gradlew build

echo "🧪 Validando SPM (iOS)"
cd iosApp
xcodebuild -resolvePackageDependencies
cd ..

echo "✅ Tudo consistente"

