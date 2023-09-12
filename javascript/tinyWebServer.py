# servidor web mínimo
#
# arquivos de servidores relativos para o diretório atual.
# servidores do diretório cgi-bin python cgis.

import BaseHTTPServer
import CGIHTTPServer

PORT = 8000

class HTTPHandler(CGIHTTPServer.CGIHTTPRequestHandler):
    """
    servidor web simples que pode auxiliar strings em um url de request.

    """

    def do_GET(self):
        # simplehttpserver não sabe como auxiliar as strings da query
        # pedidos 'GET', então processá-los aqui:
        if self.path.find('?') != -1:
            self.path, self.query_string = self.path.split('?', 1)
        else:
            self.query_string = ''

        # carregar no resto do processamento...
        CGIHTTPServer.CGIHTTPRequestHandler.do_GET(self)

if __name__ == '__main__':
    server_address = ('', PORT)
    httpd = BaseHTTPServer.HTTPServer(server_address, HTTPHandler)

    print "servindo no porto", PORT
    httpd.serve_forever()
