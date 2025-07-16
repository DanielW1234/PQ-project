#!/usr/bin/env python3
import pymysql


def main():
    conn = pymysql.connect(
        host='localhost',
        user='root',
        password='Tc753211@@',
        db='popquiz_db',
        charset='utf8mb4'
    )
    cur = conn.cursor(pymysql.cursors.DictCursor)

    cur.execute("SELECT * FROM users ORDER BY id")
    rows = cur.fetchall()
    if not rows:
        print("⚠️  No users found.")
    else:
        print("🧑‍💻 Users:")
        for r in rows:
            print(f"  [{r['id']}] {r['username']}  role={r['role']}  nick={r['nickname']}  anon={r['is_anonymous']}")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()
