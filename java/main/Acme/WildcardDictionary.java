// wildcarddictionary - um dicionário com lookups de wildcard
//
// copyright (c) 1996 por jef poskanzer <jef@acme.com>. todos os direitos reservados.
//
// redistribuição e o uso na fonte e forms binários, com ou sem
// modificação, são permitidos seguindo as seguintes condições:
//
// 1. redistribuições do código fonte deve o notice de copyright acima,
//    essa lista de condições e o seguinte disclaimer.
// 2. redistribuições em form binário deve reproduzir o copyright acima
//    essa lista de condições e o seguinte disclaimer na documentação e/ou
//    outros materiais fornecidos com a distribuição.
//
// THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
// OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
// HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
// OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE.
//
// visite a página do acme labs java para versões atualizadas disso e
// outras utilidades java: https://acme.com/java/

package Acme;

import java.util.*;

// um dicionário com lookups de wildcard
// <P>
// as chaves nesse dicionário são caminhos wildcard. quando você realiza um get(),
// a string que você passa coincide com todos os caminhos, e o primeiro
// é retornado.
// <P>
// o matcher do wildcard é simples, ele implementa o * significando qualquer
// string, ? significando qualquer caractere sozinho e | separando caminhos
// múltiplos. todos os outros caracteres são literais.
// <P>
// <A HREF="/resources/classes/Acme/WildcardDictionary.java">captura o software.</A><BR>
// <A HREF="/resources/classes/Acme.tar.Z">captura o pacote acme inteiro.</A>
// <P>
// @see Acme.Utils#match

public class WildcardDictionary extends Dictionary
    {

    private Vector keys;
    private Vector elements;

    /// construtor
    public WildcardDictionary()
        {
        keys = new Vector();
        elements = new Vector();
        }

    /// retorna o número de elementos existentes sem o dicionário
    public int size()
        {
        return elements.size();
        }

    /// retorna true se o dicionário contém nenhum elemento
    public boolean isEmpty()
        {
        return size() == 0;
        }

    /// retorna uma enumeração das chaves do dicionário
    public Enumeration keys()
        {
        return keys.elements();
        }

    /// retorna uma enumeração do número de elementos. use os métodos
    // enumeration no objeto retornado para capturar os elementos sequencialmente
    public Enumeration elements()
        {
        return elements.elements();
        }

    /// obtém o objeto associado com a chave especificada no dicionário.
    // a chave é assumida para ser uma string, que coincide com
    // as chaves wildcard-pattern no dicionário.
    //
    // @param key a string
    // @returns o elemento para a chave, ou null caso não tenha
    // @see Acme.Utils#match
    public synchronized Object get( Object key )
    {
        String sKey = (String) key;

        int matching_len = 0, found = -1;

        // para otimizar a velocidade, as chaves devem estar armazenadas pelo tamanho
        // TODO: acima
        for ( int i = keys.size()-1; i > -1; i-- )
        {
            String thisKey = (String) keys.elementAt( i );

            int current = Acme.Utils.matchSpan( thisKey, sKey );

            if (current > matching_len)
            {
                found = i;
                matching_len = current;
            }
        }

        if (found > -1)
            return elements.elementAt( found );

        return null;
    }

    public static String trimPathSeparators(String src) {
        StringBuffer result = new StringBuffer(src.length());

        boolean ms = false;

        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);

            if (c == '/' || c == '\\') {
                if (!ms) {
                    result.append(c);

                    ms = true;
                }
            } else {
                result.append(c);

                ms = false;
            }
        }

        return result.toString();
    }

    }
