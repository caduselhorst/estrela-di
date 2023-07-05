package br.com.estrela.config;

import java.io.File;
import java.util.List;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public final class SftpClient {

	private static final int SESSION_TIMEOUT = 10000;
	private static final int CHANNEL_TIMEOUT = 5000;

	private Configuracao configuracao;

	public void send(List<File> files) {

		Session jschSession = null;

		try {

			JSch jsch = new JSch();
			
			File knownHosts = new File(configuracao.getFtpHostsConhecidos());
			
			if(!knownHosts.exists()) {
				throw new RuntimeException("Arquivo de hosts não encontrado.");
			}

			jsch.setKnownHosts(knownHosts.getAbsolutePath());
			

			jschSession = jsch.getSession(configuracao.getFtpUsuario(), configuracao.getFtpHost(), configuracao.getFtpPort());
			jschSession.setPassword(configuracao.getFtpSenha());
			jschSession.connect(SESSION_TIMEOUT);

			final Channel sftp = jschSession.openChannel("sftp");
			sftp.connect(CHANNEL_TIMEOUT);

			final ChannelSftp channelSftp = (ChannelSftp) sftp;

			for (File file : files) {

				try {
					log.info("Enviando arquivo " + file.getAbsolutePath());
					channelSftp.put(file.getAbsolutePath(), configuracao.getFtpPath());
					log.info("Arquivo enviado.");

				} catch (SftpException e) {
					log.error(e.getMessage(), e);
				}

			}

		} catch (JSchException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (jschSession != null) {
				jschSession.disconnect();
			}
		}

	}

}
