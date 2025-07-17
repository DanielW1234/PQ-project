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
    cur = conn.cursor()

    user_id = input('User ID to update: ').strip()
    print("可更新字段：username, password, role, nickname, is_anonymous")
    field = input('Field to update: ').strip()
    value = input('New value: ').strip()

    sql = f"UPDATE users SET {field} = %s WHERE id = %s"
    cur.execute(sql, (value, user_id))
    conn.commit()

    if cur.rowcount:
        print(f"✔ Updated user {user_id}, {cur.rowcount} row(s) affected.")
    else:
        print(f"⚠️  No user with id={user_id} or no change made.")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()
