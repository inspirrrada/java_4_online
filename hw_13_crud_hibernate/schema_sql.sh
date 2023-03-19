 echo "Create schema if doesnt exist"
    echo "Please enter root user MySQL password"
       read pwd
       mysql -u root -p$pwd --execute="CREATE SCHEMA IF NOT EXISTS gameplay;"
