@echo off
cd /d "%~dp0"
title TiendaComprasBD

echo Iniciando proyecto TiendaComprasBD...
echo.

set "MAVEN="

where mvn >nul 2>nul
if %errorlevel%==0 (
    set "MAVEN=mvn"
    goto ejecutar
)

if exist "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.1\plugins\maven\lib\maven3\bin\mvn.cmd" (
    set "MAVEN=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.1\plugins\maven\lib\maven3\bin\mvn.cmd"
    goto ejecutar
)

if exist "C:\Pogram Files\JetBrains\IntelliJ IDEA Community Edition 2024.1\plugins\maven\lib\maven3\bin\mvn.cmd" (
    set "MAVEN=C:\Pogram Files\JetBrains\IntelliJ IDEA Community Edition 2024.1\plugins\maven\lib\maven3\bin\mvn.cmd"
    goto ejecutar
)

if exist "C:\Program Files\Apache NetBeans\netbeans\java\maven\bin\mvn.cmd" (
    set "MAVEN=C:\Program Files\Apache NetBeans\netbeans\java\maven\bin\mvn.cmd"
    goto ejecutar
)

if exist "C:\Program Files\NetBeans-18\netbeans\java\maven\bin\mvn.cmd" (
    set "MAVEN=C:\Program Files\NetBeans-18\netbeans\java\maven\bin\mvn.cmd"
    goto ejecutar
)

for /f "delims=" %%i in ('where /r "C:\Program Files" mvn.cmd 2^>nul') do (
    set "MAVEN=%%i"
    goto ejecutar
)

for /f "delims=" %%i in ('where /r "C:\Program Files (x86)" mvn.cmd 2^>nul') do (
    set "MAVEN=%%i"
    goto ejecutar
)

echo No se encontro Maven automaticamente.
echo.
echo Abra NetBeans, clic derecho al proyecto, Run Maven, Goals...
echo y escriba: clean spring-boot:run -DskipTests
echo.
pause
exit /b

:ejecutar
echo Maven encontrado en: %MAVEN%
echo.
"%MAVEN%" clean spring-boot:run -DskipTests
pause
