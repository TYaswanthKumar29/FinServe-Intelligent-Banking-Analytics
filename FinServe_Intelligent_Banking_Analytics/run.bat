@echo off
setlocal EnableExtensions EnableDelayedExpansion
cd /d "%~dp0"

echo ==========================================
echo FinServe - Intelligent Banking Analytics
echo Java 21 launcher
echo ==========================================
echo.

where java >nul 2>nul
if errorlevel 1 (
  echo ERROR: Java was not found in PATH.
  echo Install a JDK 21 and configure JAVA_HOME/PATH.
  pause
  exit /b 1
)

where javac >nul 2>nul
if errorlevel 1 (
  echo ERROR: javac was not found. A full JDK is required.
  echo Configure IntelliJ or install a full JDK 21.
  pause
  exit /b 1
)

if exist out rmdir /s /q out
mkdir out
if exist sources.txt del sources.txt

rem Use forward slashes in the javac argument file because backslashes
rem are treated as escapes by Java's @argfile parser on Windows.
for /r "src\main\java" %%f in (*.java) do (
  set "source=%%f"
  set "source=!source:\=/!"
  echo "!source!">>sources.txt
)

javac --release 21 -encoding UTF-8 -d out @sources.txt
if errorlevel 1 (
  echo.
  echo BUILD FAILED. Check the compiler errors above.
  del sources.txt
  pause
  exit /b 1
)

del sources.txt
echo.
echo Build successful. Starting FinServe...
echo.
java -cp out com.finserve.app.FinServeApplication
set "APP_EXIT=%ERRORLEVEL%"
echo.
pause
exit /b %APP_EXIT%
