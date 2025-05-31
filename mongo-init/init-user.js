db.createUser({
    user: "root",
    pwd: "rootpassword",
    roles: [
      { role: "readWrite", db: "persona_db" },
      { role: "dbAdmin", db: "persona_db" }
    ]
  });
  