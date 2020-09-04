from flask import Flask
from flask import request
from flask import make_response
import uuid

app = Flask(__name__)


@app.route('/')
def hello_world():
    cookies = request.cookies
    if 'sessionId' in cookies:
        response = make_response(f'기존 연결입니다: sessionId={cookies["sessionId"]}')
    else:
        new_session_id = str(uuid.uuid4())
        response = make_response(f'새 연결입니다: sessionId={new_session_id}')

        # 쿠키 만료시간은 5초입니다.
        response.set_cookie('sessionId', new_session_id, max_age=5)
    print(response.headers.get('Content-Type'))
    return response


if __name__ == '__main__':
    app.run()
