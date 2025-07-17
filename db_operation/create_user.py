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

    # 从标准输入读取新用户信息
    username = input('Username: ')
    password = input('Password: ')
    role = input('Role (organizer/speaker/audience): ')
    nickname = input('Nickname (optional): ')
    is_anonymous = input('Is anonymous? (0=No, 1=Yes): ')

    sql = """
        INSERT INTO users
          (username, password, role, nickname, is_anonymous)
        VALUES
          (%s,       %s,       %s,   %s,       %s)
    """
    cur.execute(sql, (username, password, role, nickname or None, is_anonymous))
    conn.commit()
    print(f"✔ Created user with id = {cur.lastrowid}")

    cur.close()
    conn.close()


if __name__ == '__main__':
    main()


