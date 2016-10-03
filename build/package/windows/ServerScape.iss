;This file will be executed next to the application bundle image
;I.e. current directory will contain folder ServerScape with application files
[Setup]
AppId={{fxApplication}}
AppName=ServerScape
AppVersion=0.1
AppVerName=ServerScape 0.1
AppPublisher=Splitmane
AppComments=ServerScape
AppCopyright=Copyright (C) 2015
;AppPublisherURL=http://java.com/
;AppSupportURL=http://java.com/
;AppUpdatesURL=http://java.com/
DefaultDirName={pf}\ServerScape
DisableStartupPrompt=Yes
DisableDirPage=No
DisableProgramGroupPage=Yes
DisableReadyPage=Yes
DisableFinishedPage=Yes
DisableWelcomePage=Yes
DefaultGroupName=Splitmane
;Optional License
LicenseFile=
;WinXP or above
MinVersion=0,5.1 
OutputBaseFilename=ServerScape-0.1
Compression=lzma
SolidCompression=yes
PrivilegesRequired=admin
SetupIconFile=ServerScape\ServerScape.ico
UninstallDisplayIcon={app}\ServerScape.ico
UninstallDisplayName=ServerScape
WizardImageStretch=No
WizardSmallImageFile=ServerScape-setup-icon.bmp   
ArchitecturesInstallIn64BitMode=x64


[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Files]
Source: "ServerScape\ServerScape.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "ServerScape\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\ServerScape"; Filename: "{app}\ServerScape.exe"; WorkingDir: "{app}"; IconFilename: "{app}\ServerScape.ico"; Comment: "ServerScape"; 
Name: "{commondesktop}\ServerScape"; Filename: "{app}\ServerScape.exe"; WorkingDir: "{app}"; IconFilename: "{app}\ServerScape.ico"; Comment: "ServerScape";

[Run]
Filename: "{app}\ServerScape.exe"; Parameters: "-Xappcds:generatecache"; Check: returnFalse()
Filename: "{app}\ServerScape.exe"; Description: "{cm:LaunchProgram,ServerScape}"; Flags: nowait postinstall skipifsilent; Check: returnTrue()
Filename: "{app}\ServerScape.exe"; Parameters: "-install -svcName ""ServerScape"" -svcDesc ""ServerScape"" -mainExe ""ServerScape.exe""  "; Check: returnFalse()

[UninstallRun]
Filename: "{app}\ServerScape.exe "; Parameters: "-uninstall -svcName ServerScape -stopOnUninstall"; Check: returnFalse()

[Code]
function returnTrue(): Boolean;
begin
  Result := True;
end;

function returnFalse(): Boolean;
begin
  Result := False;
end;

function InitializeSetup(): Boolean;
begin
// Possible future improvements:
//   if version less or same => just launch app
//   if upgrade => check if same app is running and wait for it to exit
//   Add pack200/unpack200 support? 
  Result := True;
end;  
