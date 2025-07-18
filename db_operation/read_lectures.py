#!/usr/bin/env python3
import pymysql


def main():
    conn = pymysql.connect(
        host='localhost', user='root', password='Tc753211@@',
        db='popquiz_db', charset='utf8mb4'
    )
    cur = conn.cursor(pymysql.cursors.DictCursor)

    cur.execute("SELECT * FROM lectures ORDER BY id")
    rows = cur.fetchall()
    if not rows:
        print("⚠️  No lectures found.")
    else:
        print("🎙️  Lectures:")
        for r in rows:
            print(f"[{r['id']}] {r['title']}")
            print(f"    desc      : {r['description']}")
            print(f"    organizer : {r['organizer_id']}   speaker : {r['speaker_id']}")
            print(f"    start_time: {r['start_time']}   end_time: {r['end_time']}")
            print(f"    password  : {r['password']}\n")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()
