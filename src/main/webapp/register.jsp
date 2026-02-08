<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TrimLink - Register</title>

<style>

body {
    margin: 0;
    padding: 0;
    background-color: #121212;
    font-family: Arial, sans-serif;
    color: white;
}

/* Navbar */
.navbar {
    background-color: #1f1f1f;
    padding: 15px;
    text-align: center;
    font-size: 24px;
    font-weight: bold;
    letter-spacing: 1px;
    border-bottom: 2px solid #333;
}

/* Center container */
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 90vh;
}

/* Form box */
.form-box {
    background-color: #1e1e1e;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 0 15px rgba(0,0,0,0.6);
    width: 350px;
}

.form-box h2 {
    text-align: center;
    margin-bottom: 20px;
}

.input-group {
    margin-bottom: 15px;
}

.input-group label {
    display: block;
    margin-bottom: 5px;
}

.input-group input {
    width: 100%;
    padding: 10px;
    border: none;
    border-radius: 5px;
    background-color: #2a2a2a;
    color: white;
}

.input-group input:focus {
    outline: 2px solid #4CAF50;
}

.btn {
    width: 100%;
    padding: 10px;
    background-color: #4CAF50;
    border: none;
    border-radius: 5px;
    color: white;
    font-size: 16px;
    cursor: pointer;
    margin-top: 10px;
}

.btn:hover {
    background-color: #45a049;
}

.link {
    text-align: center;
    margin-top: 10px;
}

.link a {
    color: #4CAF50;
    text-decoration: none;
}

</style>

</head>
<body>

<div class="navbar">
    TrimLink
</div>

<div class="container">
    <div class="form-box">

        <h2>Register</h2>

        <form action="register" method="post">

            <div class="input-group">
                <label>Username</label>
                <input type="text" name="user_name" required>
            </div>

            <div class="input-group">
                <label>Password</label>
                <input type="password" name="password" required>
            </div>

            <button type="submit" class="btn">Register</button>

        </form>

        <div class="link">
            Already have an account? <a href="login.jsp">Login</a>
        </div>

    </div>
</div>

</body>
</html>
