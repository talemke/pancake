package org.slf4j.impl;

import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.MessageFormatter;

class LoggerImpl implements Logger {

	/* Generic */
	@Override
	public String getName() {
		return StaticLoggerBinder.REDIRECT_TO.getName();
	}
	/* Generic */





	/* Trace */
	@Override
	public boolean isTraceEnabled() {
		return StaticLoggerBinder.REDIRECT_TO.isLoggable(Level.FINER);
	}

	@Override
	public void trace(String msg) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.FINER, msg);
	}

	@Override
	public void trace(String format, Object arg) {
		trace(MessageFormatter.format(format, arg).getMessage());
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		trace(MessageFormatter.format(format, arg1, arg2).getMessage());

	}

	@Override
	public void trace(String format, Object... arguments) {
		trace(MessageFormatter.format(format, arguments).getMessage());

	}

	@Override
	public void trace(String msg, Throwable t) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.FINER, msg, t);
	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		return StaticLoggerBinder.REDIRECT_TO.isLoggable(Level.FINER);
	}

	@Override
	public void trace(Marker marker, String msg) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.FINER, "@" + marker.getName() + " | " + msg);
	}

	@Override
	public void trace(Marker marker, String format, Object arg) {
		trace(marker, MessageFormatter.format(format, arg).getMessage());
	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		trace(marker, MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void trace(Marker marker, String format, Object... argArray) {
		trace(marker, MessageFormatter.format(format, argArray).getMessage());
	}

	@Override
	public void trace(Marker marker, String msg, Throwable t) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.FINER, "@" + marker.getName() + " | " + msg, t);
	}
	/* Trace */





	/* Debug */
	@Override
	public boolean isDebugEnabled() {
		return StaticLoggerBinder.REDIRECT_TO.isLoggable(Level.FINE);
	}

	@Override
	public void debug(String msg) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.FINE, msg);
	}

	@Override
	public void debug(String format, Object arg) {
		debug(MessageFormatter.format(format, arg).getMessage());
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		debug(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void debug(String format, Object... arguments) {
		debug(MessageFormatter.format(format, arguments).getMessage());
	}

	@Override
	public void debug(String msg, Throwable t) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.FINE, msg, t);
	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		return StaticLoggerBinder.REDIRECT_TO.isLoggable(Level.FINE);
	}

	@Override
	public void debug(Marker marker, String msg) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.FINE, "@" + marker.getName() + " | " + msg);
	}

	@Override
	public void debug(Marker marker, String format, Object arg) {
		debug(marker, MessageFormatter.format(format, arg).getMessage());
	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		debug(marker, MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void debug(Marker marker, String format, Object... arguments) {
		debug(marker, MessageFormatter.format(format, arguments).getMessage());
	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.FINE, "@" + marker.getName() + " | " + msg, t);
	}
	/* Debug */





	/* Info */
	@Override
	public boolean isInfoEnabled() {
		return StaticLoggerBinder.REDIRECT_TO.isLoggable(Level.INFO);
	}

	@Override
	public void info(String msg) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.INFO, msg);
	}

	@Override
	public void info(String format, Object arg) {
		info(MessageFormatter.format(format, arg).getMessage());
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		info(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void info(String format, Object... arguments) {
		info(MessageFormatter.format(format, arguments).getMessage());
	}

	@Override
	public void info(String msg, Throwable t) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.INFO, msg, t);
	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		return StaticLoggerBinder.REDIRECT_TO.isLoggable(Level.INFO);
	}

	@Override
	public void info(Marker marker, String msg) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.INFO, "@" + marker.getName() + " | " + msg);
	}

	@Override
	public void info(Marker marker, String format, Object arg) {
		info(marker, MessageFormatter.format(format, arg).getMessage());
	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		info(marker, MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void info(Marker marker, String format, Object... arguments) {
		info(marker, MessageFormatter.format(format, arguments).getMessage());
	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.INFO, "@" + marker.getName() + " | " + msg, t);
	}
	/* Info */





	/* Warn */
	@Override
	public boolean isWarnEnabled() {
		return StaticLoggerBinder.REDIRECT_TO.isLoggable(Level.WARNING);
	}

	@Override
	public void warn(String msg) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.WARNING, msg);
	}

	@Override
	public void warn(String format, Object arg) {
		warn(MessageFormatter.format(format, arg).getMessage());
	}

	@Override
	public void warn(String format, Object... arguments) {
		warn(MessageFormatter.format(format, arguments).getMessage());
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		warn(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void warn(String msg, Throwable t) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.WARNING, msg, t);
	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		return StaticLoggerBinder.REDIRECT_TO.isLoggable(Level.WARNING);
	}

	@Override
	public void warn(Marker marker, String msg) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.WARNING, "@" + marker.getName() + " | " + msg);
	}

	@Override
	public void warn(Marker marker, String format, Object arg) {
		warn(marker, MessageFormatter.format(format, arg).getMessage());
	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		warn(marker, MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void warn(Marker marker, String format, Object... arguments) {
		warn(marker, MessageFormatter.format(format, arguments).getMessage());
	}

	@Override
	public void warn(Marker marker, String msg, Throwable t) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.WARNING, "@" + marker.getName() + " | " + msg, t);
	}
	/* Warn */





	/* Error */
	@Override
	public boolean isErrorEnabled() {
		return StaticLoggerBinder.REDIRECT_TO.isLoggable(Level.SEVERE);
	}

	@Override
	public void error(String msg) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.SEVERE, msg);
	}

	@Override
	public void error(String format, Object arg) {
		error(MessageFormatter.format(format, arg).getMessage());
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		error(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void error(String format, Object... arguments) {
		error(MessageFormatter.format(format, arguments).getMessage());
	}

	@Override
	public void error(String msg, Throwable t) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.SEVERE, msg, t);
	}

	@Override
	public boolean isErrorEnabled(Marker marker) {
		return StaticLoggerBinder.REDIRECT_TO.isLoggable(Level.SEVERE);
	}

	@Override
	public void error(Marker marker, String msg) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.SEVERE, "@" + marker.getName() + " | " + msg);
	}

	@Override
	public void error(Marker marker, String format, Object arg) {
		error(marker, MessageFormatter.format(format, arg).getMessage());
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {
		error(marker, MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {
		error(marker, MessageFormatter.format(format, arguments).getMessage());
	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {
		StaticLoggerBinder.REDIRECT_TO.log(Level.SEVERE, "@" + marker.getName() + " | " + msg, t);
	}
	/* Error */

}
