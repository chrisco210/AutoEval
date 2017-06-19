param (
	[string]$path = $env:programfiles
)

$client = new-object System.Net.WebClient
$client.DownloadFile("http://dl.rachlinski.cf/AutoEval/versions/beta/beta-1/AutoEval-Beta1.jar", "C:\\users\\Christopher\\desktop\\")

 