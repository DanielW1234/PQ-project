#!/usr/bin/env python3
import pymysql
from datetime import datetime


def main():
    conn = pymysql.connect(
        host='localhost', user='root', password='Tc753211@@',
        db='popquiz_db', charset='utf8mb4'
    )
    cur = conn.cursor()

    lecture_id = input('Lecture ID: ').strip()
    content_type = input("Content Type (ppt/pdf/text/audio): ").strip()
    original_name = input('Original Filename: ').strip()
    storage_path = input('Storage Path: ').strip()
    file_length = input('File Length (pages): ').strip() or '0'
    is_download = input('Allow Download? (0=No,1=Yes): ').strip() or '1'

    sql = """
    INSERT INTO lecture_contents
      (lecture_id, content_type, original_name, storage_path, file_length, is_download)
    VALUES
      (%s,          %s,           %s,            %s,           %s,          %s)
    """
    cur.execute(sql, (
        int(lecture_id), content_type, original_name,
        storage_path, int(file_length), int(is_download)
    ))
    conn.commit()
    print(f"✔ Created content id = {cur.lastrowid}")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()
