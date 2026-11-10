#!/usr/bin/env python3
"""Minimal LDAP/middleware mock for local Home Remittance login.
Called by: docker/ldap-mock/Dockerfile CMD; compose service ldap-mock.
"""
from http.server import BaseHTTPRequestHandler, HTTPServer
import json

PORT = 8089


class Handler(BaseHTTPRequestHandler):
    def _json(self, code, payload):
        body = json.dumps(payload).encode("utf-8")
        self.send_response(code)
        self.send_header("Content-Type", "application/json")
        self.send_header("Content-Length", str(len(body)))
        self.send_header("Access-Control-Allow-Origin", "*")
        self.end_headers()
        self.wfile.write(body)

    def do_OPTIONS(self):
        self.send_response(204)
        self.send_header("Access-Control-Allow-Origin", "*")
        self.send_header("Access-Control-Allow-Methods", "POST, OPTIONS")
        self.send_header("Access-Control-Allow-Headers", "*")
        self.end_headers()

    def do_POST(self):
        length = int(self.headers.get("Content-Length", 0))
        if length:
            self.rfile.read(length)
        self._json(
            200,
            {
                "status": "OK",
                "message": "Login successful (mock)",
                "body": {
                    "employeeID": "1001",
                    "branchCode": "0001",
                    "mail": "admin@localhost",
                    "displayName": "Local Admin",
                    "name": "admin",
                    "title": "Administrator",
                },
            },
        )

    def log_message(self, fmt, *args):
        print("[%s] %s" % (self.log_date_time_string(), fmt % args))


if __name__ == "__main__":
    print("LDAP mock listening on :%s" % PORT)
    HTTPServer(("0.0.0.0", PORT), Handler).serve_forever()
