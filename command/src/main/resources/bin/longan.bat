@echo off
REM save current dir
set curPath=%cd%
set curDisk=%curPath:~0,2%
REM echo %curPath%
REM echo %curDisk%

REM get exe-cmd file path
set cmdFilePath=%~dp0
set cmdDisk=%~d0
%cmdDisk%
cd %cmdFilePath%\..\lib
set parentPath=%cd%
REM echo %parentPath%

REM return current dir
%curDisk%
cd %curPath%

REM set class env
setlocal enabledelayedexpansion
set clsStr=
for /f %%s in ('dir !parentpath! /a-d /b') do (set "clsStr=!clsStr!!parentPath!\%%s;")
set clsStr=!CLASSPATH!;!clsStr!
REM echo %clsStr%
set CLASSPATH=%clsStr%
java org.ranji.longan.command.Longan
endlocal


