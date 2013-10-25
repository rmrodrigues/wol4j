package com.rmrodrigues.network.wol;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import com.rmrodrigues.network.wol.validator.MacAddressValidator;

/**
 * The Class WOLNode.
 */
public class WOLNode {

	/** The Constant EMPTY_STIRNG. */
	private static final String EMPTY_STIRNG = "";

	/** The Constant BROADCAST_IP. */
	private static final String BROADCAST_IP = "255.255.255.255";

	/** The Constant WOL_PORT. */
	private static final int WOL_PORT = 9;

	/** The mac address. */
	private String macAddress = null;

	/**
	 * Instantiates a new wOL node.
	 * 
	 * @param macAddress
	 *            the mac address
	 */
	public WOLNode(String macAddress) {
		super();
		this.macAddress = macAddress;
	}

	/**
	 * Wake up.
	 */
	public void wakeUP() {
		try {
			validate();
			send();
		} catch (Exception e) {
			System.out.println("Erro:" + e.getLocalizedMessage());
		}

	}

	/**
	 * Validate.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	private void validate() throws IllegalArgumentException {

		if (this.macAddress == null || this.macAddress != null
				&& EMPTY_STIRNG.equals(this.macAddress.trim())) {
			throw new IllegalArgumentException(
					"The Mac address must be provided.");
		}

		if (!MacAddressValidator.validate(macAddress)) {
			throw new IllegalArgumentException("Invalid MAC address.");
		}

	}

	/**
	 * Send.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	private void send() throws Exception {
		DatagramSocket socket = null;
		try {
			byte[] macBytes = getMacBytes();
			byte[] bytes = new byte[6 + 16 * macBytes.length];
			for (int i = 0; i < 6; i++) {
				bytes[i] = (byte) 0xff;
			}
			for (int i = 6; i < bytes.length; i += macBytes.length) {
				System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
			}

			InetAddress address = InetAddress.getByName(BROADCAST_IP);
			DatagramPacket packet = new DatagramPacket(bytes, bytes.length,
					address, WOL_PORT);
			socket = new DatagramSocket();
			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}

	/**
	 * Clean.
	 * 
	 * @return the string
	 */
	private void clean() {
		macAddress = macAddress.replaceAll("\\:|\\.|\\-", "");
	}

	/**
	 * Split.
	 * 
	 * @return the string[]
	 */
	private String[] split() {
		System.out.println("Split: " + macAddress);
		List<String> splittedMac = new ArrayList<String>();
		String[] result = new String[6];
		for (int i = 0; i < macAddress.length(); i = i + 2) {
			splittedMac.add(macAddress.substring(i, i + 2));
		}
		splittedMac.toArray(result);
		return result;
	}

	/**
	 * Gets the mac bytes.
	 * 
	 * @return the mac bytes
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	private byte[] getMacBytes() throws IllegalArgumentException {
		clean();
		byte[] bytes = new byte[6];
		String[] hex = split();
		for (int i = 0; i < 6; i++) {
			bytes[i] = (byte) Integer.parseInt(hex[i], 16);
		}
		return bytes;
	}

}
