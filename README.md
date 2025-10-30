# ElectronicsProjectManagerGIT
<!DOCTYPE html>
<html>
<head>

</head>
<body>

<h1>Tutorial de Setup do git</h1>

<h3>Connection to git</h3>
<p>git init</p>

<h3>Defines who is using this git connection</h3>
<p>git config --global user.name "insert user"</p>
<p>git config --global user.email "insert email"</p>

<h3>Origin becomes the repo references, so push origin means push to this repo git </h3>
<p>remote add origin https://github.com/your account/your repo.git</p>

<h3>Remote connection check</h3>
<p>git remote -v</p>

<h3>Pull everything from repo</h3>
<p>git fetch origin</p>

<h3>Check branch</h3>
<p>git branch --list</p>

<h3>Change branch</h3>
<p>gith switch branch-name</p>

<h3>Check all actions in repo</h3>
<p>git log</p>

<h3>Stage changes</h3>
<p>git add .</p>

<h3>Make commit</h3>
<p>git commit -m "commit-message"</p>

<h3>Push to repo</h3>
<p>git push repo-reference</p>

<h1>Tutorial de Setup do MariaDB</h1>

<h3>Install MariaDB</h3>
<p>sudo apt install mariadb-server</p>

<h3>Acessar mariadb server</h3>
<p>sudo mariadb</p>

<h3>Configurar acesso</h3>
<h4>(apos o sudo mariadb)</h4>
<p>config access</p>

<h3>Remover privilegios</h3>
<h4>(apos o sudo mariadb)</h4>
<p>flush privileges</p>

<h3>Acesso sem sudo</h3>
<h4>(apos o sudo mariadb)</h4>
<p>mariadb -u root -p (enter password when prompted)</p>

</body>
</html>

