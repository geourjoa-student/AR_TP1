/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aor.printing;

import java.io.Serializable;

/**
 * l'ensemble des notifications pouvant être échangées entre le client et le serveur.
 * @author Morat 
 */
public enum Notification implements Serializable {
	QUERY_JOBS, QUERY_PRINT, QUERY_STATUS, REPLY_JOBS, REPLY_PRINT_ENDED, REPLY_PRINT_ERROR, REPLY_PRINT_OK, REPLY_STATUS, REPLY_UNKNOWN_ERROR, REPLY_UNKNOWN_NOTIFICATION
}


