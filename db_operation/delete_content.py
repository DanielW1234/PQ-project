#!/usr/bin/env python3
import pymysql


def main():
    conn = pymysql.connect(
        host='localhost', user='root', password='Tc753211@@',
        db='popquiz_db', charset='utf8mb4'
    )
    cur = conn.cursor()

    cid = input('Content ID to delete: ').strip()
    confirm = input(f"Are you sure to DELETE content id={cid}? (y/N): ").strip().lower()
    if confirm != 'y':
        print("❌ Aborted.")
    else:
        cur.execute("DELETE FROM lecture_contents WHERE id = %s", (cid,))
        conn.commit()
        if cur.rowcount:
            print(f"✔ Deleted content id={cid}.")
        else:
            print(f"⚠️  No record with id={cid}.")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()
