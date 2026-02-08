<%@ page import="java.util.List" %>
<%@ page import="com.trimlink.models.Url" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TrimLink - Dashboard</title>

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
    padding: 15px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid #333;
}

.project-name {
    font-size: 24px;
    font-weight: bold;
    letter-spacing: 1px;
}

.logout-btn {
    background-color: #ff4c4c;
    border: none;
    border-radius: 5px;
    color: white;
    padding: 8px 15px;
    cursor: pointer;
    font-size: 14px;
}

.logout-btn:hover {
    background-color: #e04343;
}

/* Form styles */
.container {
    display: flex;
    justify-content: center;
    margin-top: 30px;
}

.form-box {
    background-color: #1e1e1e;
    padding: 30px;
    border-radius: 10px;
    width: 450px;
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

.btn {
    width: 100%;
    padding: 10px;
    background-color: #4CAF50;
    border: none;
    border-radius: 5px;
    color: white;
    cursor: pointer;
}

/* Table styles */
.table-container {
    margin: 30px;
}

table {
    width: 100%;
    border-collapse: collapse;
    background-color: #1e1e1e;
}

th, td {
    padding: 10px;
    border: 1px solid #333;
    text-align: center;
}

th {
    background-color: #2a2a2a;
}

.delete-btn {
    background-color: #ff4c4c;
    border: none;
    color: white;
    padding: 6px 12px;
    border-radius: 5px;
    cursor: pointer;
}

</style>

</head>
<body>

<div class="navbar">
    <div class="project-name">TrimLink</div>

    <form action="logout" method="post">
        <button type="submit" class="logout-btn">Logout</button>
    </form>
</div>

<!-- FORM SECTION  -->
<div class="container">
    <div class="form-box">

        <h2>Create Short Link</h2>

        <form action="urlcontroller" method="post">

            <div class="input-group">
                <label>Long URL</label>
                <input type="text" name="long_url" required>
            </div>

            <div class="input-group">
                <label>Custom Slug</label>
                <input type="text" name="slug" required>
            </div>

            <button type="submit" class="btn">Generate Link</button>

        </form>

    </div>
</div>

<!-- TABLE SECTION  -->
<div class="table-container">

    <h2>Your Shortened URLs</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Long URL</th>
            <th>Short URL</th>
            <th>Action</th>
        </tr>

        <%
            List<Url> urls = (List<Url>) request.getAttribute("urls");

            if(urls != null) {
                for(Url u : urls) 
                {
        %>

				        <tr>
				            <td><%= u.getUrl_id() %></td>
				            <td><%= u.getLong_url() %></td>
				            <td>
							    <a target="_blank" href="<%= "redirectShortUrl/" + u.getShort_url() %>">
							        <%= u.getShort_url() %>
							    </a>
							</td>

				
				            <td>
				                <form action="deleteurl" method="post">
				                    <input type="hidden" name="url_id" value="<%= u.getUrl_id() %>">
				                    <button type="submit" class="delete-btn">Delete</button>
				                </form>
				            </td>
				        </tr>

        <%
                }
            }
            
            else {
        %>

			        <tr>
			            <td colspan="4">No URLs Found</td>
			        </tr>

        <%
            }
        %>

    </table>

</div>

</body>
</html>
