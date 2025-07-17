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

    user_id = input('User ID to delete: ').strip()
    confirm = input(f"Are you sure to DELETE user id={user_id}? (y/N): ").strip().lower()
    if confirm != 'y':
        print("❌ Aborted.")
    else:
        cur.execute("DELETE FROM users WHERE id = %s", (user_id,))
        conn.commit()
        if cur.rowcount:
            print(f"✔ Deleted user id={user_id}.")
        else:
            print(f"⚠️  No user with id={user_id}.")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()
