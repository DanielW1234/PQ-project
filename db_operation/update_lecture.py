#!/usr/bin/env python3
import pymysql


def main():
    conn = pymysql.connect(
        host='localhost', user='root', password='Tc753211@@',
        db='popquiz_db', charset='utf8mb4'
    )
    cur = conn.cursor()

    lec_id = input('Lecture ID to update: ').strip()
    print("可更新字段：title, description, organizer_id, speaker_id, start_time, end_time, password")
    field = input('Field to update: ').strip()
    value = input('New value: ').strip() or None

    sql = f"UPDATE lectures SET {field} = %s WHERE id = %s"
    cur.execute(sql, (value, lec_id))
    conn.commit()

    if cur.rowcount:
        print(f"✔ Updated lecture {lec_id}, {cur.rowcount} row(s) affected.")
    else:
        print(f"⚠️  No lecture with id={lec_id} or no change made.")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()
