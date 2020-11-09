package com.bestinfo.canal.client.type;

/**
 * 游戏权限
 * 
 * @author XBox
 *
 */
public class GamePermission {
	/** 投注权限：0—禁止，1—允许 */
	public int bet;
	/** 兑奖权限：0—禁止，1—允许 */
	public int cash;
	/** 注销权限：0—禁止，1—允许 */
	public int cancel;

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
}