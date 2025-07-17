#!/usr/bin/env python3
import pymysql
from datetime import datetime


def main():
    conn = pymysql.connect(
        host='localhost', user='root', password='Tc753211@@',
        db='popquiz_db', charset='utf8mb4'
    )
    cur = conn.cursor()

    title = input('Title: ')
    description = input('Description: ')
    organizer_id = input('Organizer user_id: ')
    speaker_id = input('Speaker user_id: ')
    start_time = input('Start time (YYYY-MM-DD HH:MM:SS): ')
    end_time = input('End time   (YYYY-MM-DD HH:MM:SS) or leave blank: ') or None
    password = input('Room password (optional): ') or None

    sql = """
    INSERT INTO lectures
      (title, description, organizer_id, speaker_id, start_time, end_time, password)
    VALUES
      (%s,    %s,          %s,           %s,         %s,         %s,       %s)
    """
    cur.execute(sql, (
        title, description, int(organizer_id), int(speaker_id),
        start_time, end_time, password
    ))
    conn.commit()
    print(f"✔ Created lecture id = {cur.lastrowid}")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()
