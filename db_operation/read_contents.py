#!/usr/bin/env python3
import pymysql


def main():
    conn = pymysql.connect(
        host='localhost', user='root', password='Tc753211@@',
        db='popquiz_db', charset='utf8mb4'
    )
    cur = conn.cursor(pymysql.cursors.DictCursor)

    cur.execute("SELECT * FROM lecture_contents ORDER BY id")
    rows = cur.fetchall()
    if not rows:
        print("⚠️  No contents found.")
    else:
        print("📄 Lecture Contents:")
        for r in rows:
            print(f"[{r['id']}] Lecture {r['lecture_id']}  Type={r['content_type']}")
            print(f"    original_name: {r['original_name']}")
            print(f"    storage_path : {r['storage_path']}")
            print(f"    pages         : {r['file_length']}  download? {r['is_download']}")
            print(f"    uploaded_at   : {r['uploaded_at']}")
            print(f"    processed_at  : {r['processed_at']}")
            print(f"    processed_text preview: {(r['processed_text'] or '')[:50]}\n")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()
