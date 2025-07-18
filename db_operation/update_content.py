#!/usr/bin/env python3
import pymysql


def main():
    conn = pymysql.connect(
        host='localhost', user='root', password='Tc753211@@',
        db='popquiz_db', charset='utf8mb4'
    )
    cur = conn.cursor()

    cid = input('Content ID to update: ').strip()
    print(
        "可更新字段：lecture_id, content_type, original_name, storage_path, processed_text, processed_at, is_download, file_length")
    field = input('Field to update: ').strip()
    value = input('New value: ').strip() or None

    # 如果更新的是数字或枚举，MySQL 会自动转换；processed_text 可能很长，可直接传入
    sql = f"UPDATE lecture_contents SET {field} = %s WHERE id = %s"
    cur.execute(sql, (value, cid))
    conn.commit()

    if cur.rowcount:
        print(f"✔ Updated content {cid}, {cur.rowcount} row(s) affected.")
    else:
        print(f"⚠️  No record with id={cid} or no change made.")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()
